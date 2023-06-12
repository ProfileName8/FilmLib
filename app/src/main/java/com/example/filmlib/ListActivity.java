package com.example.filmlib;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Button b1 = (Button) findViewById(R.id.Add_Item);

        //Создание ListVIew
        ListView filmList = findViewById(R.id.list_of_films);

        //Создание списка фильмов, которые будут в приложении
        Film[] films = {
                new Film("Очень интересный фильм","Не просмотрено","0", "ну очень нойс фильм"),
                new Film("Очень интересный фильм","Не просмотрено","0", "ну очень нойс фильм"),
                new Film("Очень интересный фильм","Не просмотрено","0", "ну очень нойс фильм"),
        };
        //Объединение
        films = mergeArrays(films,readFromPublicStorage());
        //подключение адаптера
        FilmListAdapter filmListAdapter = new FilmListAdapter(this, films);
        filmList.setAdapter(filmListAdapter);
        //Добавление нового Item по кнопке
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInPublicStorage("Очень Классный/Не смотрел/324/Ну кайф чо.");
                filmListAdapter.notifyDataSetChanged();
            }
        });
        filmListAdapter.notifyDataSetChanged();
    }

    //Добавить в общее хранилище
    public void addInPublicStorage(String content) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(getExternalFilesDir(null), "FilmDataFile.txt");

            try {
                FileWriter writer = new FileWriter(file, true);
                writer.append(content);
                writer.append("\n");
                writer.flush();
                writer.close();
                Log.d("ListActivity", "Файл создан");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("ListActivity", "Файл не создан: " + e.getMessage());
            }
        } else {
            Log.d("ListActivity", "Внешнее хранилище недоступно");
        }
    }

    //Считать из файла данных
    public Film[] readFromPublicStorage() {
        Film[] films = {};
        File file = new File(getExternalFilesDir(null), "FilmDataFile.txt");
        StringBuilder content = new StringBuilder();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("/");
                films = addFilmToArray(films, new Film(parts[0],parts[1],parts[2],parts[3]));
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return films;
    }

    //Метод для добавленияв список
    public Film[] addFilmToArray(Film[] films, Film newFilm) {
        Film[] newFilms = new Film[films.length + 1];
        System.arraycopy(films, 0, newFilms, 0, films.length);
        newFilms[films.length] = newFilm;
        return newFilms;
    }

    //Объединить массивы
    public Film[] mergeArrays(Film[] array1, Film[] array2) {
        Film[] mergedArray = new Film[array1.length + array2.length];
        System.arraycopy(array1, 0, mergedArray, 0, array1.length);
        System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);
        return mergedArray;
    }
}