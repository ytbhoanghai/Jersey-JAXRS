package com.nguyen.implService;

import com.nguyen.entity.ResponseEntity;
import com.nguyen.entity.Text;
import com.nguyen.exception.NotFoundTextException;
import com.nguyen.filter.Authorization;
import com.nguyen.service.TextService;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path(value = "/text")
public class TextServiceImpl implements TextService {

    private static List<Text> TEXT_LIST;
    private static List<String> tokens;

    static {
        TEXT_LIST = new ArrayList<>();
        TEXT_LIST.add(new Text(1,"ytb", "Nguyễn Hoàng Hải"));
        TEXT_LIST.add(new Text(2, "root", "Tôi là Admin"));
        TEXT_LIST.add(new Text(3, "abc", "hoanghai"));

        tokens = new ArrayList<>();
    }


    @Override @Authorization
    public Response insert(Text text) {
        System.out.println("Insert Text: " + text);

        TEXT_LIST.add(text);
        ResponseEntity entity = new ResponseEntity(
                Response.Status.OK, new Date(), "Process success !!!");

        return Response.ok(entity).build();
    }

    @Override @Authorization
    public Response selectById(Integer id) {
        System.out.println("Select Text By Id: " + id);

        Text text = TextServiceImpl.findTextById(id, TEXT_LIST);
        if (text == null) {
            throw new NotFoundTextException(id);
        }

        return Response.ok(text).build();
    }

    @Override @Authorization
    public Response selectAll() {
        System.out.println("Select All Text");

        return Response.ok(TEXT_LIST).build();
    }

    @Override @Authorization
    public Response update(Text text) {
        System.out.println("Update Text: " + text);

        Integer id = text.getId();
        Text _text = TextServiceImpl.findTextById(id, TEXT_LIST);
        if (_text == null) {
            throw new NotFoundTextException(id);
        }
        TEXT_LIST.remove(_text);
        TEXT_LIST.add(text);
        return Response.ok("Update text success, id: " + id).type(MediaType.TEXT_PLAIN).build();
    }

    @Override @Authorization
    public Response delete(Integer id) {
        System.out.println("Delete Text By Id: " + id);

        Text text = TextServiceImpl.findTextById(id, TEXT_LIST);
        if (text == null) {
            throw new NotFoundTextException(id);
        }
        TEXT_LIST.remove(text);
        return Response.ok("Delete text success, id: " + id).type(MediaType.TEXT_PLAIN).build();
    }

    @Override
    public Response getToken() {
        String token = UUID.randomUUID().toString();
        System.out.println("Get Token: " + token);

        tokens.add(token);
        return Response.ok("ytb" + token).build();
    }

    @Override
    public Response testToken(String token) {
        token = token.substring("ytb".length());

        if (!TextServiceImpl.existToken(token)) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("Token is not found").
                    build();
        }
        return Response.ok("Token is exist").build();
    }

    private static Text findTextById(Integer id, List<Text> TEXT_LIST) {
        Optional<Text> optionalText = TEXT_LIST.stream().
                filter(text -> text.getId().equals(id)).
                findFirst();

        return optionalText.orElse(null);
    }

    public static boolean existToken(String token) {
        return tokens.contains(token);
    }
}
