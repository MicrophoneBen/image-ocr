package com.ghostben.image.entity.viewmodels;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : Microphoneben
 * @date : 2018/10/24
 * @description : UploadEvent
 */
@Getter
@Setter
public class UploadEvent {
    private String eventType = "progress";

    private Object state;

}
