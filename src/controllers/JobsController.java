package controllers;

import core.Controller;
import entities.Inscription;
import entities.User;
import entities.Vacant;
import models.VacanteModel;
import models.repository.impl.InscriptionRepository;
import views.app.JobsView;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JobsController extends Controller {
    private JobsView jobsView;
    private final VacanteModel vacanteModel = new VacanteModel();
    private final InscriptionRepository inscriptionRepository = new InscriptionRepository();
    private List<Vacant> pendingList  = Collections.emptyList();
    private List<Vacant> acceptedList = Collections.emptyList();
    private List<Vacant> rejectedList = Collections.emptyList();
    private String lastQuery = "";

    @Override
    public void run() {
        jobsView = new JobsView(this);
        addView("JobsView", jobsView);
    }

    public JobsView getView() {
        return jobsView;
    }

    public void onUserChanged(User user) {
        if (user == null) {
            pendingList = acceptedList = rejectedList = Collections.emptyList();
        } else {
            String dni = user.getDni();

            vacanteModel.loadVacantes();
            List<Vacant> allVac = vacanteModel.getVacantes();
            List<Inscription> inscs = inscriptionRepository.list();

            pendingList = inscs.stream()
                    .filter(i -> i.getCandidatoDni().equals(dni) && "EN_CURSO".equals(i.getEstado()))
                    .map(i -> findVacantById(allVac, i.getVacanteId()))
                    .filter(v -> v != null)
                    .collect(Collectors.toList());

            acceptedList = inscs.stream()
                    .filter(i -> i.getCandidatoDni().equals(dni) && "ACEPTADO".equals(i.getEstado()))
                    .map(i -> findVacantById(allVac, i.getVacanteId()))
                    .filter(v -> v != null)
                    .collect(Collectors.toList());

            rejectedList = inscs.stream()
                    .filter(i -> i.getCandidatoDni().equals(dni) && "RECHAZADO".equals(i.getEstado()))
                    .map(i -> findVacantById(allVac, i.getVacanteId()))
                    .filter(v -> v != null)
                    .collect(Collectors.toList());
        }

        applyFilter(lastQuery);
    }

    private Vacant findVacantById(List<Vacant> list, long id) {
        return list.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void filterPostulations(String query) {
        lastQuery = query == null ? "" : query.trim();
        applyFilter(lastQuery);
    }

    private void applyFilter(String query) {
        String lc = query.toLowerCase();

        List<Vacant> p = pendingList.stream()
                .filter(vacant -> matches(vacant, lc))
                .collect(Collectors.toList());

        List<Vacant> a = acceptedList.stream()
                .filter(vacant -> matches(vacant, lc))
                .collect(Collectors.toList());

        List<Vacant> r = rejectedList.stream()
                .filter(vacant -> matches(vacant, lc))
                .collect(Collectors.toList());

        jobsView.setPostulations(p, a, r);
    }

    private boolean matches(Vacant v, String q) {
        return q.isEmpty() || v.getTitulo().toLowerCase().contains(q) || v.getUbicacion().toLowerCase().contains(q);
    }

    public void viewDetails(Vacant v) {
        JOptionPane.showMessageDialog(jobsView,
                new JScrollPane(new JTextArea(v.getDescripcion())),
                v.getTitulo(), JOptionPane.INFORMATION_MESSAGE);
    }
}
