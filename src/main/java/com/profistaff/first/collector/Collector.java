package com.profistaff.first.collector;

import com.profistaff.first.exception.IOWrapper;
import com.profistaff.first.exception.NoSuchFolderException;

import java.io.*;
import java.util.*;

public class Collector {

    /**
     * Объединяет содержимое всех файлов корневой папки,
     * включая файлы из вложенных папок
     * @param path абсолютный путь до корневой папки
     * @return абсолютный путь до созданного файла
     */
    public String collect(String path) {
        String name = UUID.randomUUID().toString();

        File rootFolder = new File(path);

        if (!rootFolder.exists()) {
            throw new NoSuchFolderException("No folder was found by this path " + path);
        }

        List<File> files = findAllFiles(rootFolder);

        files.sort((file1, file2)-> file1.getAbsolutePath().compareTo(file2.getAbsolutePath()));

        File collectedFile = new File(rootFolder, name);

        try {
            collectedFile.createNewFile();
        } catch (IOException ex) {
            throw new IOWrapper(ex);
        }

        writeAllInFile(collectedFile, files);

        return collectedFile.getAbsolutePath();
    }

    private void writeAllInFile(File target, List<File> files) {
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(target);
        } catch (FileNotFoundException e) {
            throw new IOWrapper(e);
        }

        for (File file : files) {
            try {
                outputStream.write(readContent(file));
            } catch (IOException e) {
                throw new IOWrapper(e);
            }
        }
    }

    /**
     * Считываем байты из файла
     * @param file файл из которого нужно считать байты
     * @return массив байтов
     */
    private byte[] readContent(File file) {
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(file);

            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);

            return bytes;
        } catch (IOException ex) {
            throw new IOWrapper(ex);
        }
    }

    /**
     * Получить список всех файлов директории и поддиректорий
     * @param rootFolder абсолютный путь до директории
     * @return список файлов
     */
    private List<File> findAllFiles(File rootFolder) {

        List<File> files = new ArrayList<>(Arrays.asList(rootFolder.listFiles()));

        int i = 0;
        while (i < files.size()) {
            File file = files.get(i);
            if (file.isDirectory()) {
                List<File> innerList = findAllFiles(file);
                files.remove(i);
                files.addAll(innerList);
            }
            i++;
        }

        return files;
    }
}
