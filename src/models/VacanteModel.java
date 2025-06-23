package models;

import entities.BusinessUser;
import entities.Inscription;
import entities.Vacant;
import models.repository.impl.InscriptionRepository;
import models.repository.impl.VacantRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VacanteModel {
    private final VacantRepository vacantRepository;
    private final InscriptionRepository inscriptionRepository;
    private final List<Vacant> vacants = new ArrayList<>();

    public VacanteModel() {
        this.vacantRepository = new VacantRepository();
        this.inscriptionRepository = new InscriptionRepository();
    }

    public void loadVacantes() {
        vacants.clear();
        vacants.addAll(vacantRepository.list());
    }

    public boolean postulate(long vacanteId, String candidatoDni) {
        try {
            inscriptionRepository.create(new Inscription(candidatoDni, vacanteId, "EN_CURSO", LocalDateTime.now().toString()));
            loadVacantes();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Vacant> search(String location, String query) {
        String q = (query == null ? "" : query).trim().toLowerCase();
        return vacants.stream()
                .filter(v -> v.getUbicacion().equalsIgnoreCase(location))
                .filter(v -> q.isEmpty() || v.getTitulo().toLowerCase().contains(q) || v.getDescripcion().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public List<Vacant> getVacantes() {
        return Collections.unmodifiableList(vacants);
    }

}
