-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2023 at 07:08 AM
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

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `nama` varchar(40) DEFAULT NULL,
  `gender` varchar(40) DEFAULT NULL,
  `nim` int(9) DEFAULT NULL,
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
('orang', 'ewr', 15339571, '0000-00-00', 'sdfsadf', '432423', 'eqwrqewr', '21', 'ewqrewqr', NULL, NULL, NULL),
('', 'Laki-laki', 209750, '2023-11-15', 'dsfadsaf', '4132', 'dfsadsa', 'Computer Science', 'FASILKOM-TI', 'New Student', 'Non Disabilities', 23132),
('dsasd', 'Perempuan', 206161, '2023-11-08', 'baru', '3248334', 'baru@usu', 'Electronics', 'FH', 'Drop Out', 'Disabilities', 432123),
('', 'Laki-laki', 205126, '2023-11-20', 'dsadas', 'dsasad', 'dsasa', 'Computer Science', 'FK', 'New Student', 'Non Disabilities', 213),
('fadsds', 'Laki-laki', 204933, '2023-11-07', 'dfsdsfads', '543453453', 'fsdadsfdfs', 'Computer Science', 'FK', 'New Student', 'Non Disabilities', 342342);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `prodi`
--
ALTER TABLE `prodi`
  ADD PRIMARY KEY (`id_prodi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
