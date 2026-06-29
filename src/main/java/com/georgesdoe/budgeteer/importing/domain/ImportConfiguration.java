package com.georgesdoe.budgeteer.importing.domain;

import com.georgesdoe.budgeteer.importing.domain.parsing.FileParserConfiguration;
import lombok.Data;

@Data
public class ImportConfiguration {
    FileParserConfiguration fileConfiguration;
}
