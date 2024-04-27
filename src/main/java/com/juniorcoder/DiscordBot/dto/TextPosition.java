package com.juniorcoder.DiscordBot.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

@Data @Builder
public class TextPosition {
    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment verticalAlignment;
    private short fontSize;
    private boolean bold;

    public short getRealFontSize() {
        return (short) (fontSize * 20);
    }
}
