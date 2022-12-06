package com.georgesdoe.budgeteer.domain.importing;

import com.georgesdoe.budgeteer.domain.importing.parsing.FileParserConfiguration;
import lombok.Data;

@Data
public class ImportConfiguration {
    FileParserConfiguration fileConfiguration;

    Integer memberId;
}
