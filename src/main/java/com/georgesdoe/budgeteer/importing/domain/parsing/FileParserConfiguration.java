package com.georgesdoe.budgeteer.importing.domain.parsing;

import lombok.Data;

@Data
public class FileParserConfiguration {
    String timestampField;

    String valueField;

    String descriptionField;

    String categoryField;

    String timestampFormat;

    Boolean expensesAsNegative = false;
}
