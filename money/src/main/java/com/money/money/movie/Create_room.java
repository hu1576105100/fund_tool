package com.money.money.movie;


import cn.hutool.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create_room", value = "/create_room")
public class Create_room extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String room_id = req.getParameter("room_id");
        String room_pwd = req.getParameter("room_pwd");
        String video_url = req.getParameter("video_url");

        if(!room_id.equals("") && !room_pwd.equals("") && !video_url.equals("")) {
            if(ServerManager.room_info.get(room_id) != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", 0);
                resp.getWriter().write(jsonObject.toString());
            } else {
                ServerManager.room_info.put(room_id, room_pwd);
                ServerManager.video_info.put(room_id, video_url);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", 1);
                jsonObject.put("room_id", room_id);
                jsonObject.put("room_pwd", room_pwd);
                jsonObject.put("video_url", video_url);
                resp.getWriter().write(jsonObject.toString());
            }
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            resp.getWriter().write(jsonObject.toString());
        }
    }
}
