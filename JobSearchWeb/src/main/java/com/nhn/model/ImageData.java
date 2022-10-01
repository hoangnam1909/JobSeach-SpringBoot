package com.nhn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {

    @Id
    private String id;

    private String name;

    private String type;

    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

}
