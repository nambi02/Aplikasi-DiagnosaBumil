-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Jul 2024 pada 15.06
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bumil`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `nama`, `username`, `password`) VALUES
(0, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- --------------------------------------------------------

--
-- Struktur dari tabel `diagnosa`
--

CREATE TABLE `diagnosa` (
  `idkonsultasi` int(11) NOT NULL,
  `tanggalkonsul` varchar(1000) NOT NULL,
  `id` int(11) NOT NULL,
  `nama_pasien` varchar(1000) NOT NULL,
  `umur_pasien` varchar(100) NOT NULL,
  `notelp` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `kodep` varchar(100) NOT NULL,
  `namap` varchar(100) NOT NULL,
  `deskripp` text NOT NULL,
  `saranp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `diagnosa`
--

INSERT INTO `diagnosa` (`idkonsultasi`, `tanggalkonsul`, `id`, `nama_pasien`, `umur_pasien`, `notelp`, `alamat`, `kodep`, `namap`, `deskripp`, `saranp`) VALUES
(1, '07/07/2024', 1, 'Dinda', '34', '0858764321', 'Jln. Bekasi Timur Raya No.45', 'P01', 'IUGR  ', 'Menurut (Putera et al., 2023) Intra Uterine Growth Restrictions (IUGR) didefinisikan sebagai kecepatan pertumbuhan janin kurang dari potensi pertumbuhan janin normal untuk neonatus tertentu atau kegagalan janin untuk mencapai potensi pertumbuhannya. Dalam kehidupan rahim atau selama periode postnatal suatu bayi dengan berat badan lahir atau panjang badan lahir di bawah persentil ke-10 dikenal sebagai kecil masa kehamilan. \nPenyebab terjadinya IUGR adalah multifaktorial diantaranya adalah faktor maternal, faktor fetal, faktor plasenta dan faktor genetik. Berbagai faktor ibu seperti usia ibu, jarak antar kehamilan (kurang dari 6 bulan atau 120 bulan atau lebih), kesehatan ibu, kebiasaan perilaku, dan infeksi ibu mempengaruhi pertumbuhan janin dan bertanggung jawab menyebabkan IUGR (Putera et al., 2023).\n', 'Untuk menangani kondisi IUGR pada ibu hamil, dokter menyarankan pemantauan kondisi janin lebih sering, memperhatikan pergerakan janin, menerapkan pola hidup sehat dengan mengonsumsi makanan bergizi, olahraga untuk ibu hamil, menghindari stres, serta minum suplemen atau vitamin kehamilan yang diresepkan. Ibu juga dianjurkan untuk istirahat yang cukup, baik di rumah sakit maupun di rumah. Jika IUGR terjadi pada usia kandungan 34 minggu atau lebih, dokter mungkin akan menyarankan mempercepat persalinan melalui induksi atau, jika kondisi janin berisiko tinggi, tindakan operasi caesar.'),
(2, '05/06/2024', 2, 'Laras', '25', '085811487545', 'Jln. Haji Kubah Mas No.56 ', 'P02', 'GAKY', 'Menurut (Dewi Citra, 2014) “ Gangguan akibat kurang yodium (GAKY) adalah sekumpulan gejala yang timbul karena tubuh seseorang kekurangan unsur yodium secara terus-menerus dalam jangka waktu yang cukup lama. GAKY dapat diidentifikasikan dengan adanya gondok/goiter, kretin, tingginya angka kematian bayi dan menurunnya tingkat kecerdasan (IQ). Dalam tubuh manusia Yodium diperlukan untuk membentuk hormon tiroksin yang diperlukan oleh tubuh untuk mengatur pertumbuhan dan perkembangan mulai dari janin sampai dewasa”.', 'Gangguan akibat kekurangan yodium, seperti penyakit gondok, hipotiroidisme, dan lainnya dapat dicegah dengan memasukkan garam beryodium dalam makanan serta mengonsumsi makanan yang kaya yodium, seperti telur, makanan laut (seafood), rumput laut, dan produk olahan susu.'),
(3, '07/07/2024', 3, 'Dini', '19', '0858679012', 'Jln.Haji Nawi No.87', 'P03', 'Anemia', 'Menurut Nurhaidah & Rostinah dalam (Afni et al., 2023)  “Anemia merupakan permasalahan kesehatan masyarakat dunia yang dapat meningkatkan angka kesakitan serta kematian pada ibu dan bayi. Ibu hamil yang menderita anemia mempunyai peluang mengalami perdarahan pada saat melahirkan yang dapat berakibat pada kematian. Anemia pada ibu hamil disebut potential danger to mother and child (berpotensi membahaya kan ibu dan anak). Oleh karena itu anemia memerlukan perhatian dari semua pihak yang terlibat dalam pelayanan kesehatan”.\nMenurut Prawirohardjo dalam (Afni et al., 2023) “Anemia dalam kehamilan merupakan keadaan ibu dengan kandungan hemoglobin dibawah 11 gram% pada trimester 1 serta 3 atau <10,5 gr% pada trimester 2. Anemia lebih kerap ditemukan dalam kehamilan karna dalam kehamilan kebutuhan akan zat- zat makanan meningkat serta terjadi perubahan- perubahan dalam darah dan sumsum tulang”.', 'Untuk mencegah anemia pada ibu hamil, penting memperhatikan asupan makanan dan pola hidup sehari-hari. Konsumsi makanan kaya zat besi seperti telur, daging merah, sayuran hijau, dan kacang-kacangan, serta makanan tinggi vitamin B12 seperti susu, produk olahannya, tempe, dan tahu. Selain itu, penuhi kebutuhan vitamin C harian dengan mengonsumsi buah dan sayur.'),
(4, '07/07/2024', 4, 'Neni', '25', '0878654321', 'Jln.Rungkad No.89', 'P04', 'KEK', 'Menurut Nisa dalam (Kusumastuti et al., 2023) “Chronic Energy Deficiency atau yang lebih dikenal dengan sebutan Kekurangan Energi Kronis (KEK) ialah keadaan ketika ibu hamil mengalami kekurangan makanan secara parah akan berdampak pada munculnya gangguan kesehatan yang mengakibatkan kebutuhan zat gizi ibu yang sedang hamil semakin bertambah sehingga tidak tercukupi”. \nIndividu yang mengalami KEK akan mengalami berat badan di bawah standar normal, serta mengalami gangguan pada produktivitasnya sebagai akibat dari tidak dapat bergerak aktif karena kekurangan gizi. Sementara itu, dampak KEK terhadap proses persalinan yaitu bisa menyebabkan persalinan lama serta tidak mudah, persalinan PPI atau prematur iminen, perdarahan post partum, dan meningkatnya tindakan sectio caesaria (Kusumastuti et al., 2023).', 'Secara umum, KEK pada ibu hamil bisa diatasi dengan melakukan perbaikan gizi. Akan tetapi, mungkin dokter perlu memberikan penanganan khusus bila kondisi ibu cukup parah.\nUntuk mencegah kurang gizi saat ibu hamil, ibu hamil juga perlu makan makanan padat gizi, seperti daging, ayam, telur, sayuran, buah-buahan, nasi, umbi-umbian, dan susu.'),
(5, '07/07/2024', 5, 'Katty', '27', '0887896781', 'Jln.Kondang No.90', 'P05', 'Kelahiran Prematur', 'Menurut Bobak dalam (Anasari et al., 2016) “Penyebab persalinan preterm yaitu faktor iatrogenik merupakan faktor dari kesehatan medis. Faktor maternal meliputi riwayat preterm sebelumnya, umur ibu, paritas ibu, pekerjaan, plasenta previa, kelainan serviks (serviks inkompetensi), malnutrisi, hipertensi dan trauma. Faktor janin meliputi kehamilan kembar (gemelli) dan cacat bawaan (kelainan kongenital). Faktor perilaku meliputi ibu yang merokok dan minum alkohol”.\nMenurut Amiruddin dalam (Anasari et al., 2016) “Malnutrisi pada ibu ditemukan berpengaruh terhadap pertumbuhan dan fungsi plasenta, ukuran plasenta yang kecil dan kandungan Deoxyribose-Nucleic Acid (DNA) yang tereduksi. Hal ini menunjukkan bahwa ukuran plasenta kecil maka transfer zat gizi untuk janin rendah akibatnya pertumbuhan janin terhambat sehingga mengakibatkan kelahiran preterm”.', 'Untuk mencegah dan menangani kelahiran prematur, penting bagi ibu hamil untuk menjalani perawatan prenatal rutin dan menjaga jarak kehamilan minimal 18 bulan dari kelahiran anak sebelumnya, terutama jika sebelumnya lahir prematur. Hindari alkohol, rokok, dan narkoba, karena zat-zat tersebut dapat membahayakan janin dan menyebabkan ketergantungan pada bayi yang baru lahir. Jaga kebersihan area kelahiran untuk menghindari infeksi dengan menggunakan handuk atau tisu baru setelah buang air kecil. Konsumsi asam folat yang cukup, biasanya diberikan oleh bidan saat kontrol kehamilan, untuk menutrisi bayi dan menjaga kesehatan kandungan. Selain itu, perhatikan berat badan agar tetap sehat selama hamil, memastikan berat badan ibu dan pertumbuhan janin tetap seimbang.'),
(6, '15/07/2024', 6, 'Test', '25', '08675', 'Belon ada', 'P06', 'Pusing', 'Lmao', 'Lmao');

-- --------------------------------------------------------

--
-- Struktur dari tabel `gejala`
--

CREATE TABLE `gejala` (
  `kodeg` varchar(100) NOT NULL,
  `namag` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `gejala`
--

INSERT INTO `gejala` (`kodeg`, `namag`) VALUES
('G01', 'Berat Janin Kurang Dari 90% , Berat Janin Normal'),
('G02', 'Memiliki Riwayat Hipertensi'),
('G03', 'Janin tidak bergerak dalam kandungan selama trimester kedua, terutama di atas lima bulan.'),
('G04', 'Air ketuban kurang dari 500cc ( Oligohidramnion) '),
('G05', 'Pembesaran kelenjar tiroid (Gondok)'),
('G06', 'Rambut Rontok'),
('G07', 'Siklus Menstruasi Terganggu'),
('G08', 'Detak Jantung Tidak Teratur'),
('G09', 'Merasa Lelah '),
('G10', 'Merasa Lemah'),
('G11', 'Merasa Letih '),
('G12', 'Merasa Lesu'),
('G13', 'Merasa Lunglai'),
('G14', 'Wajah Terlihat Pucat'),
('G15', 'Ukuran Lingkar Lengan Atas (LILA) Kurang dari 23,5 cm '),
('G16', 'Sering Kesemutan'),
('G17', 'Berat Badan Menurun Drastis'),
('G18', 'Rasa Lelah Terus Menerus'),
('G19', 'Kontraksi Setiap 10 Menit atau lebih sering'),
('G20', 'Nyeri Pingang dan Panggul'),
('G21', 'Kram Perut dengan atu tanpa diare'),
('G22', 'Pendarahan Vagina'),
('G23', 'Perubahan keputihan. Ditandai dengan peningkatan yang signifikan pada jumlah keputihan atau bocor'),
('G24', 'Pusing');

-- --------------------------------------------------------

--
-- Struktur dari tabel `gejalapenyakit`
--

CREATE TABLE `gejalapenyakit` (
  `id` int(11) NOT NULL,
  `kodep` varchar(255) NOT NULL,
  `kodeg` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `gejalapenyakit`
--

INSERT INTO `gejalapenyakit` (`id`, `kodep`, `kodeg`) VALUES
(17, 'P01', 'G01,G02,G03,G04'),
(18, 'P02', 'G14,G09,G05,G06,G07,G08'),
(19, 'P03', 'G14,G09,G10,G11,G12,G13'),
(20, 'P04', 'G14,G15,G16,G17,G18'),
(21, 'P05', 'G19,G20,G21,G22,G23'),
(23, 'P06', 'G24,G09,G10,G11,G12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE `pasien` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `umur` varchar(100) NOT NULL,
  `telp` varchar(100) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pasien`
--

INSERT INTO `pasien` (`id`, `nama`, `umur`, `telp`, `alamat`) VALUES
(1, 'Dinda', '34', '0858764321', 'Jln. Bekasi Timur Raya No.45'),
(2, 'Laras', '25', '085811487545', 'Jln. Haji Kubah Mas No.56 '),
(3, 'Dini', '19', '0858679012', 'Jln.Haji Nawi No.87'),
(4, 'Neni', '25', '0878654321', 'Jln.Rungkad No.89'),
(5, 'Katty', '27', '0887896781', 'Jln.Kondang No.90'),
(6, 'Test', '25', '08675', 'Belon ada'),
(7, 'hk', 'jk', 'jk', 'jk');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penyakit`
--

CREATE TABLE `penyakit` (
  `kodep` varchar(100) NOT NULL,
  `namap` varchar(100) NOT NULL,
  `deskripp` text NOT NULL,
  `saranp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `penyakit`
--

INSERT INTO `penyakit` (`kodep`, `namap`, `deskripp`, `saranp`) VALUES
('P01', 'IUGR  ', 'Menurut (Putera et al., 2023) Intra Uterine Growth Restrictions (IUGR) didefinisikan sebagai kecepatan pertumbuhan janin kurang dari potensi pertumbuhan janin normal untuk neonatus tertentu atau kegagalan janin untuk mencapai potensi pertumbuhannya. Dalam kehidupan rahim atau selama periode postnatal suatu bayi dengan berat badan lahir atau panjang badan lahir di bawah persentil ke-10 dikenal sebagai kecil masa kehamilan. \nPenyebab terjadinya IUGR adalah multifaktorial diantaranya adalah faktor maternal, faktor fetal, faktor plasenta dan faktor genetik. Berbagai faktor ibu seperti usia ibu, jarak antar kehamilan (kurang dari 6 bulan atau 120 bulan atau lebih), kesehatan ibu, kebiasaan perilaku, dan infeksi ibu mempengaruhi pertumbuhan janin dan bertanggung jawab menyebabkan IUGR (Putera et al., 2023).\n', 'Untuk menangani kondisi IUGR pada ibu hamil, dokter menyarankan pemantauan kondisi janin lebih sering, memperhatikan pergerakan janin, menerapkan pola hidup sehat dengan mengonsumsi makanan bergizi, olahraga untuk ibu hamil, menghindari stres, serta minum suplemen atau vitamin kehamilan yang diresepkan. Ibu juga dianjurkan untuk istirahat yang cukup, baik di rumah sakit maupun di rumah. Jika IUGR terjadi pada usia kandungan 34 minggu atau lebih, dokter mungkin akan menyarankan mempercepat persalinan melalui induksi atau, jika kondisi janin berisiko tinggi, tindakan operasi caesar.'),
('P02', 'GAKY', 'Menurut (Dewi Citra, 2014) “ Gangguan akibat kurang yodium (GAKY) adalah sekumpulan gejala yang timbul karena tubuh seseorang kekurangan unsur yodium secara terus-menerus dalam jangka waktu yang cukup lama. GAKY dapat diidentifikasikan dengan adanya gondok/goiter, kretin, tingginya angka kematian bayi dan menurunnya tingkat kecerdasan (IQ). Dalam tubuh manusia Yodium diperlukan untuk membentuk hormon tiroksin yang diperlukan oleh tubuh untuk mengatur pertumbuhan dan perkembangan mulai dari janin sampai dewasa”.', 'Gangguan akibat kekurangan yodium, seperti penyakit gondok, hipotiroidisme, dan lainnya dapat dicegah dengan memasukkan garam beryodium dalam makanan serta mengonsumsi makanan yang kaya yodium, seperti telur, makanan laut (seafood), rumput laut, dan produk olahan susu.'),
('P03', 'Anemia', 'Menurut Nurhaidah & Rostinah dalam (Afni et al., 2023)  “Anemia merupakan permasalahan kesehatan masyarakat dunia yang dapat meningkatkan angka kesakitan serta kematian pada ibu dan bayi. Ibu hamil yang menderita anemia mempunyai peluang mengalami perdarahan pada saat melahirkan yang dapat berakibat pada kematian. Anemia pada ibu hamil disebut potential danger to mother and child (berpotensi membahaya kan ibu dan anak). Oleh karena itu anemia memerlukan perhatian dari semua pihak yang terlibat dalam pelayanan kesehatan”.\nMenurut Prawirohardjo dalam (Afni et al., 2023) “Anemia dalam kehamilan merupakan keadaan ibu dengan kandungan hemoglobin dibawah 11 gram% pada trimester 1 serta 3 atau <10,5 gr% pada trimester 2. Anemia lebih kerap ditemukan dalam kehamilan karna dalam kehamilan kebutuhan akan zat- zat makanan meningkat serta terjadi perubahan- perubahan dalam darah dan sumsum tulang”.', 'Untuk mencegah anemia pada ibu hamil, penting memperhatikan asupan makanan dan pola hidup sehari-hari. Konsumsi makanan kaya zat besi seperti telur, daging merah, sayuran hijau, dan kacang-kacangan, serta makanan tinggi vitamin B12 seperti susu, produk olahannya, tempe, dan tahu. Selain itu, penuhi kebutuhan vitamin C harian dengan mengonsumsi buah dan sayur.'),
('P04', 'KEK', 'Menurut Nisa dalam (Kusumastuti et al., 2023) “Chronic Energy Deficiency atau yang lebih dikenal dengan sebutan Kekurangan Energi Kronis (KEK) ialah keadaan ketika ibu hamil mengalami kekurangan makanan secara parah akan berdampak pada munculnya gangguan kesehatan yang mengakibatkan kebutuhan zat gizi ibu yang sedang hamil semakin bertambah sehingga tidak tercukupi”. \nIndividu yang mengalami KEK akan mengalami berat badan di bawah standar normal, serta mengalami gangguan pada produktivitasnya sebagai akibat dari tidak dapat bergerak aktif karena kekurangan gizi. Sementara itu, dampak KEK terhadap proses persalinan yaitu bisa menyebabkan persalinan lama serta tidak mudah, persalinan PPI atau prematur iminen, perdarahan post partum, dan meningkatnya tindakan sectio caesaria (Kusumastuti et al., 2023).', 'Secara umum, KEK pada ibu hamil bisa diatasi dengan melakukan perbaikan gizi. Akan tetapi, mungkin dokter perlu memberikan penanganan khusus bila kondisi ibu cukup parah.\nUntuk mencegah kurang gizi saat ibu hamil, ibu hamil juga perlu makan makanan padat gizi, seperti daging, ayam, telur, sayuran, buah-buahan, nasi, umbi-umbian, dan susu.'),
('P05', 'Kelahiran Prematur', 'Menurut Bobak dalam (Anasari et al., 2016) “Penyebab persalinan preterm yaitu faktor iatrogenik merupakan faktor dari kesehatan medis. Faktor maternal meliputi riwayat preterm sebelumnya, umur ibu, paritas ibu, pekerjaan, plasenta previa, kelainan serviks (serviks inkompetensi), malnutrisi, hipertensi dan trauma. Faktor janin meliputi kehamilan kembar (gemelli) dan cacat bawaan (kelainan kongenital). Faktor perilaku meliputi ibu yang merokok dan minum alkohol”.\nMenurut Amiruddin dalam (Anasari et al., 2016) “Malnutrisi pada ibu ditemukan berpengaruh terhadap pertumbuhan dan fungsi plasenta, ukuran plasenta yang kecil dan kandungan Deoxyribose-Nucleic Acid (DNA) yang tereduksi. Hal ini menunjukkan bahwa ukuran plasenta kecil maka transfer zat gizi untuk janin rendah akibatnya pertumbuhan janin terhambat sehingga mengakibatkan kelahiran preterm”.', 'Untuk mencegah dan menangani kelahiran prematur, penting bagi ibu hamil untuk menjalani perawatan prenatal rutin dan menjaga jarak kehamilan minimal 18 bulan dari kelahiran anak sebelumnya, terutama jika sebelumnya lahir prematur. Hindari alkohol, rokok, dan narkoba, karena zat-zat tersebut dapat membahayakan janin dan menyebabkan ketergantungan pada bayi yang baru lahir. Jaga kebersihan area kelahiran untuk menghindari infeksi dengan menggunakan handuk atau tisu baru setelah buang air kecil. Konsumsi asam folat yang cukup, biasanya diberikan oleh bidan saat kontrol kehamilan, untuk menutrisi bayi dan menjaga kesehatan kandungan. Selain itu, perhatikan berat badan agar tetap sehat selama hamil, memastikan berat badan ibu dan pertumbuhan janin tetap seimbang.'),
('P06', 'Pusing', 'Lmao', 'Lmao');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `diagnosa`
--
ALTER TABLE `diagnosa`
  ADD PRIMARY KEY (`idkonsultasi`);

--
-- Indeks untuk tabel `gejala`
--
ALTER TABLE `gejala`
  ADD PRIMARY KEY (`kodeg`);

--
-- Indeks untuk tabel `gejalapenyakit`
--
ALTER TABLE `gejalapenyakit`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `penyakit`
--
ALTER TABLE `penyakit`
  ADD PRIMARY KEY (`kodep`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `gejalapenyakit`
--
ALTER TABLE `gejalapenyakit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
