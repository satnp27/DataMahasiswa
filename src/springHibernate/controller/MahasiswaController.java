/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springHibernate.controller;

import springHibernate.App;
import springHibernate.configuration.MahasiswaTableModel;
import springHibernate.model.Mahasiswa;
import springHibernate.view.MahasiswaView;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author GIGABYTE
 */
public class MahasiswaController {
    private final MahasiswaView mahasiswaView;
    private MahasiswaTableModel mahasiswaTableModel;
    private List<Mahasiswa> mahasiswas;

    public MahasiswaController(MahasiswaView mahasiswaView) {
        this.mahasiswaView = mahasiswaView;
    }

    public void tampilData() {
        mahasiswas = App.getMahasiswaService().getMahasiswas();
        mahasiswaTableModel = new MahasiswaTableModel(mahasiswas);
        this.mahasiswaView.getTabel().setModel(mahasiswaTableModel);
    }

    public void show() {
        int index = this.mahasiswaView.getTabel().getSelectedRow();

        this.mahasiswaView.getNpm().setText(String.valueOf(this.mahasiswaView.getTabel().getValueAt(index, 0)));
        this.mahasiswaView.getNama().setText(String.valueOf(this.mahasiswaView.getTabel().getValueAt(index, 1)));
        this.mahasiswaView.getKelas().setText(String.valueOf(this.mahasiswaView.getTabel().getValueAt(index, 2)));
        this.mahasiswaView.getAlamat().setText(String.valueOf(this.mahasiswaView.getTabel().getValueAt(index, 3)));
    }

    public void clear() {
        this.mahasiswaView.getNpm().setText("");
        this.mahasiswaView.getNama().setText("");
        this.mahasiswaView.getKelas().setText("");
        this.mahasiswaView.getAlamat().setText("");
    }

    public void saveMahasiswa() {
        String npm = this.mahasiswaView.getNpm().getText();
        String nama = this.mahasiswaView.getNama().getText();
        String kelas = this.mahasiswaView.getKelas().getText();
        String alamat = this.mahasiswaView.getAlamat().getText();

        // Validate input (add your own validation logic)
        if (npm.isEmpty() || nama.isEmpty() || kelas.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Exit the method if validation fails
        }

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNpm(npm);
        mahasiswa.setNama(nama);
        mahasiswa.setKelas(kelas);
        mahasiswa.setAlamat(alamat);

        try {
            App.getMahasiswaService().save(mahasiswa);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
            clear();
            tampilData();
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            JOptionPane.showMessageDialog(null, "Error saving data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateMahasiswa() {
    String npm = this.mahasiswaView.getNpm().getText();
    String nama = this.mahasiswaView.getNama().getText();
    String kelas = this.mahasiswaView.getKelas().getText();
    String alamat = this.mahasiswaView.getAlamat().getText();

    // Validate input (add your own validation logic)
    if (npm.isEmpty() || nama.isEmpty() || kelas.isEmpty() || alamat.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Exit the method if validation fails
    }

    Mahasiswa mahasiswa = new Mahasiswa();
    mahasiswa.setNpm(npm);
    mahasiswa.setNama(nama);
    mahasiswa.setKelas(kelas);
    mahasiswa.setAlamat(alamat);

    try {
        App.getMahasiswaService().save(mahasiswa);
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Info", JOptionPane.INFORMATION_MESSAGE);
        clear();
        tampilData();
    } catch (Exception e) {
        e.printStackTrace();  // Log the exception for debugging
        JOptionPane.showMessageDialog(null, "Error saving data", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public void deleteMahasiswa() {
        if (this.mahasiswaView.getNpm().getText() == null) {
            JOptionPane.showMessageDialog(null, "Mahasiswa belum dipilih", "error",JOptionPane.ERROR_MESSAGE);
        } else {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNpm(this.mahasiswaView.getNpm().getText());
            int pilih = JOptionPane.showConfirmDialog(null,"Apakah data ingin dihapus ?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (pilih == JOptionPane.YES_OPTION) {
                App.getMahasiswaService().delete(mahasiswa);
                JOptionPane.showMessageDialog(null,"Data Berhasil di Hapus", "info",JOptionPane.INFORMATION_MESSAGE);
                clear();
                tampilData();
            }
        }
    }
}
