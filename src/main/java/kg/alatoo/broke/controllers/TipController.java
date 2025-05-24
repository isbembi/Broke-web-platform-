package kg.alatoo.broke.controllers;

import kg.alatoo.broke.dto.TipDTO;
import kg.alatoo.broke.entities.Tip;
import kg.alatoo.broke.services.TipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tips")
public class TipController {

    private final TipService tipService;

    public TipController(TipService tipService) {
        this.tipService = tipService;
    }

    @PostMapping
    public Tip addTip(@RequestBody TipDTO dto) {
        return tipService.add(dto);
    }

    @GetMapping
    public List<Tip> getAllTips() {
        return tipService.getAll();
    }
}
