package com.nguyen.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@Builder
@XmlRootElement(name = "file")
public class FileUploadResponse {

    private String fileName;
    private Long fileSizeInByte;
}
