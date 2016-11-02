package ru.test.servlet;

import ru.test.dao.FileEntityDAO;
import ru.test.entities.FileEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * Created by turov on 02.11.2016.
 */
@WebServlet(name = "FileInfoServlet", urlPatterns = {"/fileList"})
@MultipartConfig
public class FileInfoServlet extends HttpServlet {
    FileEntityDAO fileEntityDAO = new FileEntityDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();
        Set<FileEntity> fileList = fileEntityDAO.getAllFiles();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"list\": [");
        for (FileEntity o :fileList) {
            stringBuilder.append("{");
            stringBuilder.append("\"filename\": " + "\""+ o.getFilename() +"\",");
            stringBuilder.append("\"url\": " + "\""+ o.getUrl() +"\"");
            stringBuilder.append("}");
            stringBuilder.append(",");
        }
        String string = stringBuilder.toString();
        writer.write(string.substring(0,string.length()-1));
        writer.write("]}");

        writer.close();
    }
}
