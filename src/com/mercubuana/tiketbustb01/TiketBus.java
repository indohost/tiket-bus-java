package com.mercubuana.tiketbustb01;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TiketBus {

	private JFrame frmSistemInformasiTiket;
	private JTextField txtNamaBus;
	private JTextField txtKursi;
	private JTextField txtSisaKursi;
	private JComboBox cmbJenisStudio;
	private ArrayList<Bus> daftarDataBus = new ArrayList<Bus>();
	private JPanel panelDataBus;
	private JTabbedPane tabbedPane;
	private JList listDataBus;
	private JScrollPane scrollPane;
	private JButton btnTambahData;
	private JButton btnUbahData;
	private JButton btnHapusData;
	private JButton btnSaveDataBus;
	private JButton btnBatal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TiketBus window = new TiketBus();
					window.frmSistemInformasiTiket.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TiketBus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frmSistemInformasiTiket = new JFrame();
		frmSistemInformasiTiket.setTitle("Sistem Informasi Tiket Bus");
		frmSistemInformasiTiket.setBounds(100, 100, 447, 472);
		frmSistemInformasiTiket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemInformasiTiket.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 552, 416);
		frmSistemInformasiTiket.getContentPane().add(tabbedPane);
		
		panelDataBus = new JPanel();
		tabbedPane.addTab("Data Tiket Bus", null, panelDataBus, null);
		panelDataBus.setLayout(null);
		
		JLabel lblNamaBus = new JLabel("Nama Bus:");
		lblNamaBus.setBounds(25, 213, 88, 14);
		panelDataBus.add(lblNamaBus);
		
		txtNamaBus = new JTextField();
		txtNamaBus.setBounds(149, 207, 263, 26);
		panelDataBus.add(txtNamaBus);
		txtNamaBus.setColumns(10);
		
		JLabel lblDestinasi = new JLabel("Destinasi:");
		lblDestinasi.setBounds(25, 246, 99, 16);
		panelDataBus.add(lblDestinasi);
		
		cmbJenisStudio = new JComboBox();
		cmbJenisStudio.setBounds(148, 241, 264, 26);
		panelDataBus.add(cmbJenisStudio);
		cmbJenisStudio.setModel(new DefaultComboBoxModel(new String[] {"B - Jawa Barat", "T - Jawa Tengah", "U - Jawa Timur"}));
		
		JLabel lblKursi = new JLabel("Kursi:");
		lblKursi.setBounds(25, 279, 79, 16);
		panelDataBus.add(lblKursi);
		
		txtKursi = new JTextField();
		txtKursi.setBounds(149, 274, 263, 26);
		panelDataBus.add(txtKursi);
		txtKursi.setColumns(10);
		
		JLabel lblSisaKursi = new JLabel("Sisa Kursi:");
		lblSisaKursi.setBounds(25, 311, 109, 16);
		panelDataBus.add(lblSisaKursi);
		
		txtSisaKursi = new JTextField();
		txtSisaKursi.setBounds(149, 306, 263, 26);
		panelDataBus.add(txtSisaKursi);
		txtSisaKursi.setColumns(10);
		
		btnSaveDataBus = new JButton("Rekam Data Tiket Bus Baru");
		btnSaveDataBus.setEnabled(false);
		btnSaveDataBus.setBounds(226, 343, 186, 34);
		panelDataBus.add(btnSaveDataBus);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 27, 397, 116);
		panelDataBus.add(scrollPane);
		
		listDataBus = new JList();
		listDataBus.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				handlePerubahanPilihanPadaList();
			}
		});
		scrollPane.setViewportView(listDataBus);
		listDataBus.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		btnTambahData = new JButton("Tambah Data");
		btnTambahData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tambahDataTiketBus();
			}
		});
		btnTambahData.setBounds(15, 159, 131, 29);
		panelDataBus.add(btnTambahData);
		
		btnUbahData = new JButton("Ubah Data");
		btnUbahData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ubahDataTiketBus();
			}
		});
		btnUbahData.setBounds(149, 159, 131, 29);
		panelDataBus.add(btnUbahData);
		
		btnHapusData = new JButton("Hapus Data");
		btnHapusData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hapusDataBus();
			}
		});
		btnHapusData.setBounds(281, 159, 131, 29);
		panelDataBus.add(btnHapusData);
		
		btnBatal = new JButton("Batal");
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				batalkanProsesManipulasiData();
			}
		});
		btnBatal.setEnabled(false);
		btnBatal.setBounds(151, 343, 65, 34);
		panelDataBus.add(btnBatal);
		btnSaveDataBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				handleClickRekamDataBus();
			}
		});
		bacaDataTiketBusDariStoragePermanen();
		inisialisasiAntarMukaData();
		
		
	}

	protected void rekamDataBus() {
		String namaBus = txtNamaBus.getText();
		char kategoriDestinasi = 'B';
		if (cmbJenisStudio.getSelectedIndex()==1) {
			kategoriDestinasi = 'T';
		} else if (cmbJenisStudio.getSelectedIndex()==2) {
			kategoriDestinasi = 'U';
		}
		int kapasitas = 0, sisaKursi = 0;
		try {
			kapasitas = Integer.parseInt(txtKursi.getText());
			sisaKursi = kapasitas;
			txtSisaKursi.setText(""+sisaKursi);
			txtKursi.setBackground(Color.white);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Isian kapasitas harus memiliki nilai berupa bilangan bulat. Silahkan perbaiki.");
			txtKursi.setBackground(Color.red);
			return;
		}
		
		Bus tiketBusBaru = new Bus(namaBus, kategoriDestinasi, kapasitas, sisaKursi);
		
		daftarDataBus.add(tiketBusBaru);
		
		rekamDataBusKeStoragePermanen();

		tampilkanDataBus();
		
		aktifkanSemuaTombolGUI();
		btnSaveDataBus.setEnabled(false);
		btnBatal.setEnabled(false);
		int lastIndex = listDataBus.getModel().getSize()-1;
		if (lastIndex>0) {
			listDataBus.ensureIndexIsVisible(lastIndex);
		}
	}
	
	protected void rekamPerubahanDataBus() {
		int indeksDipilih = listDataBus.getSelectedIndex();
		Bus dataBusYangAkanDiubah = (Bus) listDataBus.getSelectedValue();	
		dataBusYangAkanDiubah.setNamaBus(txtNamaBus.getText());
		dataBusYangAkanDiubah.setDestinasi('B');
		if (cmbJenisStudio.getSelectedIndex()==1) {
			dataBusYangAkanDiubah.setDestinasi('T');
		} else if (cmbJenisStudio.getSelectedIndex()==2) {
			dataBusYangAkanDiubah.setDestinasi('U'); 
		}
		
		int kapasitas = 0, sisaKursi = 0;
		try {
			kapasitas = Integer.parseInt(txtKursi.getText());
			dataBusYangAkanDiubah.setKursi(kapasitas);
			txtKursi.setBackground(Color.white);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Isian kapasitas harus memiliki nilai berupa bilangan bulat. Silahkan perbaiki.");
			txtKursi.setBackground(Color.red);
			return;
		}
		
		try {
			sisaKursi = Integer.parseInt(txtSisaKursi.getText());
			dataBusYangAkanDiubah.setSisaKursi(sisaKursi);
			txtSisaKursi.setBackground(Color.white);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Isian sisa kursi harus memiliki nilai berupa bilangan bulat. Silahkan perbaiki.");
			txtSisaKursi.setBackground(Color.red);
			return;
		}
		rekamDataBusKeStoragePermanen();
		
		listDataBus.updateUI();
		
		aktifkanSemuaTombolGUI();
		btnSaveDataBus.setEnabled(false);
		btnBatal.setEnabled(false);
		
		JOptionPane.showMessageDialog(null, "Data Tiket Bus Berhasil diubah.");
	}
	
	protected void rekamDataBusKeStoragePermanen() {
		try {
			String fileName = "data_bus.txt";
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(daftarDataBus);
			oos.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File tidak bisa ditemukan.\n Pesan Kesalahan: " + e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Terjadi error pada saat merekam data ke storage.\n Pesan Kesalahan: " + e.getMessage());
		}
	}
	
	protected void bacaDataTiketBusDariStoragePermanen() {
		try {
			String fileName = "data_bus.txt";
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			daftarDataBus = (ArrayList<Bus>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File tidak bisa ditemukan.\n Pesan Kesalahan: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Terjadi error pada saat merekam data ke storage.\n Pesan Kesalahan: " + e.getMessage());
		}
	}
	
	protected void tampilkanDataBus() {
		Bus[] dataTiketBus = new Bus[daftarDataBus.size()];
		dataTiketBus = daftarDataBus.toArray(dataTiketBus);
		listDataBus.setListData(dataTiketBus);
	}
	
	protected void handlePerubahanPilihanPadaList() {
		
		Bus objekDataBusDipilih = (Bus) listDataBus.getSelectedValue();
		
		if (null == objekDataBusDipilih) {
			int indexTerakhir = listDataBus.getModel().getSize() - 1;
			listDataBus.setSelectedIndex(indexTerakhir);
			return;
		}
		
		txtNamaBus.setText(objekDataBusDipilih.getNamaBus());
		cmbJenisStudio.setSelectedIndex(0);
		if (objekDataBusDipilih.getDestinasi()=='T') {
			cmbJenisStudio.setSelectedIndex(1);
		} else if (objekDataBusDipilih.getDestinasi()=='U') {
			cmbJenisStudio.setSelectedIndex(2);
		}
		txtKursi.setText("" + objekDataBusDipilih.getKursi());
		txtSisaKursi.setText("" + objekDataBusDipilih.getSisaKursi());
	}
	
	protected void inisialisasiAntarMukaData() {
		tampilkanDataBus();
		if (listDataBus.getModel().getSize()>0) {
			listDataBus.setSelectedIndex(0);
		}
	}
	
	protected void tambahDataTiketBus() {
		nonAktifkanSemuaTombolGUI();
		btnSaveDataBus.setEnabled(true);
		btnSaveDataBus.setText("Rekam Data Tiket Bus Baru");
		btnBatal.setEnabled(true);
		
		txtNamaBus.setText("");
		cmbJenisStudio.setSelectedIndex(0);
		txtKursi.setText("0");
		txtSisaKursi.setText("0");
	}
	
	protected void ubahDataTiketBus() {
		nonAktifkanSemuaTombolGUI();
		btnSaveDataBus.setEnabled(true);
		btnSaveDataBus.setText("Rekam perubahan Data Tiket Bus");
		btnBatal.setEnabled(true);
	}
	
	protected void nonAktifkanSemuaTombolGUI() {
		btnTambahData.setEnabled(false);
		btnUbahData.setEnabled(false);
		btnHapusData.setEnabled(false);
		btnSaveDataBus.setEnabled(false);
		btnBatal.setEnabled(false);
		listDataBus.setEnabled(false);
	}
	
	protected void aktifkanSemuaTombolGUI() {
		btnTambahData.setEnabled(true);
		btnUbahData.setEnabled(true);
		btnHapusData.setEnabled(true);
		btnSaveDataBus.setEnabled(true);
		btnBatal.setEnabled(true);
		listDataBus.setEnabled(true);
	}
	
	protected void batalkanProsesManipulasiData() {
		aktifkanSemuaTombolGUI();
		btnSaveDataBus.setEnabled(false);
		btnBatal.setEnabled(false);
		
		handlePerubahanPilihanPadaList();
	}
	
	protected void handleClickRekamDataBus() {
		if (btnSaveDataBus.getText()
				.toLowerCase().contains("baru")) {
			rekamDataBus();
		} else if (btnSaveDataBus.getText()
				.toLowerCase().contains("perubahan")) {
			rekamPerubahanDataBus();
		}
	}
	
	protected void hapusDataBus() {
		int pilihanKonfirmasi = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin untuk menghapus data tiket bus " + listDataBus.getSelectedValue(), 
				"Konfirmasi hapus data", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (pilihanKonfirmasi == JOptionPane.OK_OPTION) {
			Bus dataBusYangAkanDihapus = (Bus) listDataBus.getSelectedValue();
			int indeksDataBusDihapus = listDataBus.getSelectedIndex();
			int jumlahDataBusPadaList = listDataBus.getModel().getSize();
			daftarDataBus.remove(dataBusYangAkanDihapus);
			rekamDataBusKeStoragePermanen();
			tampilkanDataBus();
			if (indeksDataBusDihapus!=0) {
				listDataBus.setSelectedIndex(indeksDataBusDihapus-1);
			} else if (indeksDataBusDihapus == jumlahDataBusPadaList-1 && jumlahDataBusPadaList > 1) {
				listDataBus.setSelectedIndex(jumlahDataBusPadaList-1);
			} else if (indeksDataBusDihapus == 0 && jumlahDataBusPadaList > 1) {
				listDataBus.setSelectedIndex(0);
			}
			JOptionPane.showMessageDialog(null, "Success, Data tiket bus berhasil dihapus");
		}
	}

}
