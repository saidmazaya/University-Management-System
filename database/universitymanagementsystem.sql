-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2023 at 05:24 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `universitymanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `nip` int(15) NOT NULL,
  `nama` varchar(40) DEFAULT NULL,
  `gender` varchar(40) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `prodi` varchar(20) DEFAULT NULL,
  `fakultas` varchar(20) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `tahun_masuk` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`nip`, `nama`, `gender`, `tgl_lahir`, `alamat`, `no_hp`, `email`, `prodi`, `fakultas`, `status`, `kategori`, `tahun_masuk`) VALUES
(204508300, 'Tess', 'Perempuan', '2023-12-06', 'sadsaup', '21321321323', 'sdasa@gmaildate', 'Teknik Sastra', 'Teknik', 'Rector', 'Non Disabilities', 91022),
(205529883, 'Orang', 'Laki-laki', '2023-12-11', 'dfasdfsad', '213123', 'dfafs@udsffa', 'Teknologi Informasi', 'Fasilkom-TI', 'Senior Lecturer', 'Non Disabilities', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `fakultas`
--

CREATE TABLE `fakultas` (
  `id_fakultas` int(9) NOT NULL,
  `nama_fakultas` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `tanggal_berdiri` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fakultas`
--

INSERT INTO `fakultas` (`id_fakultas`, `nama_fakultas`, `email`, `alamat`, `tanggal_berdiri`) VALUES
(101961, 'Fasilkom-TI', 'fasilkom@usu.ac.id', 'dr. mansyur', '2023-11-04'),
(1014780, 'Teknik', 'teknik@usu.ac.id', 'padang bintang, saturnus', '0001-02-02'),
(1018198, 'FIBA', 'fib@usu.ac.idd', 'Jl Dr. Mansyur 89', '2023-12-05'),
(1019732, 'Fakultas Pertanian', 'pertanianusu@gmail.com', 'Jl Dr mansyur', '2023-11-09'),
(1019997, 'FK', 'fk@usu.ac.id', 'usuu', '0001-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `fee`
--

CREATE TABLE `fee` (
  `nim` int(9) NOT NULL,
  `jumlah_pembayaran` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `semester` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fee`
--

INSERT INTO `fee` (`nim`, `jumlah_pembayaran`, `status`, `semester`) VALUES
(205126, '80000000', 'Lunas', '1'),
(205126, '80000000', 'Lunas', '2'),
(205126, '80000000', 'Lunas', '3'),
(202797, '30000000', 'Lunas', '3'),
(205126, '80000000', 'Lunas', '4');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `major_fee`
--

CREATE TABLE `major_fee` (
  `nama_prodi` varchar(100) DEFAULT NULL,
  `ukt` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `major_fee`
--

INSERT INTO `major_fee` (`nama_prodi`, `ukt`) VALUES
('Teknologi Informasi', '80000000'),
('Teknik Sastra', '30000000');

-- --------------------------------------------------------

--
-- Table structure for table `prodi`
--

CREATE TABLE `prodi` (
  `nama_prodi` varchar(40) DEFAULT NULL,
  `nama_fakultas` varchar(40) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `id_prodi` varchar(30) NOT NULL,
  `tanggal_berdiri` date DEFAULT NULL,
  `alamat` varchar(200) DEFAULT NULL,
  `mahasiswa_aktif` int(10) DEFAULT NULL,
  `jumlah_dosen` int(10) DEFAULT NULL,
  `jumlah_staff` int(10) DEFAULT NULL,
  `jenjang` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prodi`
--

INSERT INTO `prodi` (`nama_prodi`, `nama_fakultas`, `email`, `id_prodi`, `tanggal_berdiri`, `alamat`, `mahasiswa_aktif`, `jumlah_dosen`, `jumlah_staff`, `jenjang`) VALUES
('Teknik Sastra', 'Teknik', 'ts@usu.ac.id', '1011599', '0002-11-03', 'johor, jamin ginting', 5, 5000, 191, 'S-3'),
('Teknologi Informasi', 'Fasilkom-TI', 'ti@usu.ac.id', '1012368', '2023-11-14', 'usu', 2133, 3213, 1234, 'S-1'),
('Ilmu Komputer', 'Fasilkom-TI', 'fsad@usu', '1012620', '2023-11-15', 'usu', 321, 123, 321, 'S-1'),
('Sastra Informatika', 'FIB', 'si@usu.ac.id', '1012835', '2023-12-06', 'Johor', 87, 23, 98, 'S-1'),
('Tes', 'FK', 'dsa@gmail', '1013931', '2023-12-06', 'dsa', 324, 432, 24233, 'S-1'),
('Teknik Kedokteran', 'FK', 'tkked@usu.ac.id', '101481', '2023-11-15', 'dr. mansyur', 342, 432, 1345, 'S-1'),
('Pendidikan Dokter', 'FK', 'pd@usu.ac.id', '1018195', '2023-11-16', 'usu', 124, 421, 213, 'S-1');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `nama` varchar(40) DEFAULT NULL,
  `gender` varchar(40) DEFAULT NULL,
  `nim` int(9) NOT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `prodi` varchar(20) DEFAULT NULL,
  `fakultas` varchar(20) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `tahun_masuk` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`nama`, `gender`, `nim`, `tgl_lahir`, `alamat`, `no_hp`, `email`, `prodi`, `fakultas`, `status`, `kategori`, `tahun_masuk`) VALUES
('Aul', 'Perempuan', 202797, '2050-11-03', 'Teladan', '01111111222', 'asahan@gmail.com', 'Teknik Sastra', 'Teknik', 'Drop Out', 'Non Disabilities', 3000),
('Said Mazaya', 'Laki-laki', 204597, '2023-12-05', 'AH Nasution', '0819231234', 'said@usu.ac.id', 'Sastra Informatika', 'FIB', 'New Student', 'Non Disabilities', 2080),
('Tes', 'Laki-laki', 204933, '2023-11-07', 'dfsdsfads', '5434532', 'fsdadsfdfs', 'Teknik Kedokteran', 'FK', 'New Student', 'Non Disabilities', 342342),
('Update', 'Laki-laki', 205126, '2023-11-20', 'dsadas', 'dsasad', 'dsasa', 'Teknologi Informasi', 'Fasilkom-TI', 'New Student', 'Non Disabilities', 213),
('dasdsa', 'Perempuan', 206161, '2023-11-08', 'baru', '3248334', 'baru@usu', 'Ilmu Komputer', 'Fasilkom-TI', 'Drop Out', 'Disabilities', 432123),
('Apaa', 'Laki-laki', 209750, '2023-11-15', 'dsfadsaf', '4132', 'dfsadsa', 'Ilmu Komputer', 'Fasilkom-TI', 'New Student', 'Non Disabilities', 23132);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `nama_matkul` varchar(100) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `nama_prodi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `nama_matkul`, `semester`, `nama_prodi`) VALUES
(1011954, 'MSBD', 3, 'Teknologi Informasi'),
(10921321, 'PBOLup', 3, 'Teknologi Informasi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `fakultas`
--
ALTER TABLE `fakultas`
  ADD PRIMARY KEY (`id_fakultas`);

--
-- Indexes for table `prodi`
--
ALTER TABLE `prodi`
  ADD PRIMARY KEY (`id_prodi`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
