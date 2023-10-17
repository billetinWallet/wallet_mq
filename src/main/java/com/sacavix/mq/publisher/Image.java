package com.sacavix.mq.publisher;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Image implements Serializable {
   private String id_user;
   private String url_image;
   private String type;

   public Image(Image image) {
      id_user = image.id_user;
      url_image = image.url_image;
      type = image.type;
   }

}
