package com.georgesdoe.budgeteer.domain.importing;

import com.georgesdoe.budgeteer.domain.importing.parsing.FileParserConfiguration;
import lombok.Data;

import java.time.Instant;

@Data
public class ImportConfiguration {
    FileParserConfiguration fileConfiguration;

    Integer memberId;

    Instant startFrom;

    Instant endAt;
}
