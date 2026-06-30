package com.georgesdoe.budgeteer.importing.domain;

import com.georgesdoe.budgeteer.importing.domain.parsing.FileParserConfiguration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Configuration describing how to parse an uploaded statement file and which account to import into.")
@Data
public class ImportConfiguration {
    @Schema(description = "How to locate and interpret the relevant columns within the uploaded file.")
    FileParserConfiguration fileConfiguration;

    @Schema(description = "Identifier of the account the parsed transactions should be assigned to.", example = "1")
    Long accountId;
}
