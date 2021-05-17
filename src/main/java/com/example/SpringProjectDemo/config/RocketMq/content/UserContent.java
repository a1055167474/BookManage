package com.example.SpringProjectDemo.config.RocketMq.content;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author qinzhibin
 * @description
 * @date 2021/3/29
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Getter
@Data
public class UserContent {

    private String username;

    private String pwd;

}
