package com.money.money.movie;


import cn.hutool.json.JSONObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "join_room", value = "/join_room")
public class Join_room extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String join_room_id = req.getParameter("join_room_id");
        String join_room_pwd = req.getParameter("join_room_pwd");
        if(!join_room_id.equals("") && !join_room_pwd.equals("")) {
            if(join_room_pwd.equals(ServerManager.room_info.get(join_room_id))) {
                String video_url = ServerManager.video_info.get(join_room_id);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", 1);
                jsonObject.put("room_id", join_room_id);
                jsonObject.put("room_pwd", join_room_pwd);
                jsonObject.put("video_url", video_url);
                resp.getWriter().write(jsonObject.toString());
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", 0);
                resp.getWriter().write(jsonObject.toString());
            }
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 0);
            resp.getWriter().write(jsonObject.toString());
        }
    }
}
