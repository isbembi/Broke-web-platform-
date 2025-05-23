package kg.alatoo.broke.services;

import kg.alatoo.broke.dto.TipDTO;
import kg.alatoo.broke.entities.Tip;
import kg.alatoo.broke.repositories.TipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipService {

    private final TipRepository tipRepository;

    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    public Tip add(TipDTO dto) {
        Tip tip = new Tip();
        tip.setText(dto.getText());
        return tipRepository.save(tip);
    }

    public List<Tip> getAll() {
        return tipRepository.findAll();
    }
}
