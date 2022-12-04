package com.georgesdoe.budgeteer.domain.importing.parsing;

import lombok.Data;

@Data
public class FileParserConfiguration {
    String timestampField;

    String valueField;

    String descriptionField;

    String categoryField;

    String timestampFormat;
}
