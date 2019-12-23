package com.walker.lambda.bug;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author walker
 * @date 2019/6/11
 */
public class ListToMap {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("1", "user"));
        users.add(new User("2", null));
        // NPE
        Map<String, String> userToId = users.stream().collect(Collectors.toMap(User::getId, User::getUsername));
        Map<String, String> userMap = users.stream().collect(HashMap::new, (k, v) -> k.put(v.getId(), v.getUsername()), HashMap::putAll);
        System.out.println(userMap);
    }

    @Data
    @AllArgsConstructor
    static class User {

        private String id;
        private String username;

    }
}
