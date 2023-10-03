package petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/literature")
public class LiteratureInfoController {
    private final LiteratureInfoService literatureInfoService;

    @Autowired
    public LiteratureInfoController(LiteratureInfoService literatureInfoService) {
        this.literatureInfoService = literatureInfoService;
    }
    @GetMapping()
    public List<LiteratureInfoModel> getLiterature( ) {
        return literatureInfoService.getLiterature();
    }
    @PostMapping()
    public void addNewLiterature(LiteratureInfoModel literatureInfoModel) {
        literatureInfoService.addNewLiterature(literatureInfoModel);
    }
    @PatchMapping(path = "/{literatureId}")
    public void updateLiterature(
            @PathVariable("literatureId")
            Long literatureId,
            @RequestParam
            Integer pages,
            @RequestParam
            Integer words,
            @RequestParam
            String title,
            @RequestParam
            BigDecimal price,
            @RequestParam
            String description,
            @RequestParam
            Long authorId,
            @RequestParam
            Long genreId
            ) {
        literatureInfoService.updateLiteratureFields(literatureId,
                pages,
                words,
                title,
                price,
                description,
                authorId,
                genreId);
    }
    @PutMapping(path = "/{literatureId}")
    public void updateLiterature(
            @PathVariable("literatureId")
            Long literatureId,
            @Valid
            @RequestBody
            LiteratureInfoModel literatureInfoModel
            ) {
        literatureInfoService.updateLiterature(literatureId, literatureInfoModel);
    }
    @DeleteMapping(path = "/{literatureId}")
    public void deleteLiterature(
            @PathVariable("literatureId")
            Long literatureId
            ) {
        literatureInfoService.deleteLiterature(literatureId);
    }

}
