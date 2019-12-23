package com.walker.lambda.bug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author walker
 * @date 2019/6/11
 */
public class ListToMap {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("1", "user"));
        users.add(new User("2", null));
        Map<String, String> userMap = users.stream().collect(HashMap::new, (k, v) -> k.put(v.getId(), v.getUsername()), HashMap::putAll);
        System.out.println(userMap);
    }

    static class User {

        private String id;

        private String username;

        public User(String id, String username) {
            this.id = id;
            this.username = username;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
