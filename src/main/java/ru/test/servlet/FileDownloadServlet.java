package ru.test.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Автор: Туров Данил
 * Отвечает за загрузку файлов с сервера на клиент
 * Обрабатывает GET запросы по url: /download
 * 02.11.2016.
 */
@WebServlet(name = "FileDownloadServlet", urlPatterns = {"/download"})
@MultipartConfig
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("filename"); //берем имя файла
        OutputStream out = response.getOutputStream();  //получаем поток в ответ сервера
        FileInputStream in = new FileInputStream(filename); //поток с жесткого диска
        byte[] buffer = new byte[4096];     //буффер
        int length; //размер файла
        while ((length = in.read(buffer)) > 0){ //пока не закончится буфер читаем
            out.write(buffer, 0, length);   //и пишем
        }
        in.close();
        out.flush();
    }
}
