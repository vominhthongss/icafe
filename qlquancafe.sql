-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 24, 2020 lúc 06:24 AM
-- Phiên bản máy phục vụ: 10.4.14-MariaDB
-- Phiên bản PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlquancafe`
--

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `CAP_NHAT_SO_LUONG` (`tennguyenlieu` VARCHAR(50), `soluong` INT)  begin
    update kho set kho.soluong=kho.soluong+soLuong WHERE kho.tennguyenlieu=tenNguyenLieu;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CAP_NHAT_TRANG_THAI_HOA_DON_NHAP` (`mhd` CHAR(8))  BEGIN
	UPDATE hoadonnhap SET thanhtoan=1 WHERE hoadonnhap.mahoadonnhap=mhd;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GHI_CHU_DONG` (`maca` CHAR(8), `ghichudong` VARCHAR(200))  begin
    UPDATE ghichuca SET ghichuca.ghichudong=ghichudong WHERE ghichuca.maca=maca; 
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GHI_CHU_NHAN` (`maca` CHAR(8), `tendangnhap` CHAR(50), `ngay` DATE, `ghichunhan` VARCHAR(200), `ghichudong` VARCHAR(200))  begin
	insert into ghichuca values (maca,tendangnhap,ngay,ghichunhan,ghichudong);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GHI_HOA_DON_CHUYEN_BAN` (`ban` CHAR(8), `mahoadon` CHAR(8))  BEGIN
	UPDATE trangthai SET trangthai.ban=ban WHERE trangthai.mahoadon=mahoadon;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GHI_HOA_DON_VAO_BAN` (`ban` CHAR(8), `mahoadon` CHAR(8))  begin
    insert into trangthai VALUES (ban,mahoadon);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GHI_LAI_SO_LUONG_KHO` (`tennguyenlieu` VARCHAR(50), `soluong` INT)  BEGIN
	UPDATE kho SET kho.soluong=soluong WHERE kho.tennguyenlieu=tennguyenlieu;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GHI_TRANG_THAI_CA` (`tendangnhap` CHAR(50), `maca` CHAR(8))  begin
    insert into trangthaica values (tendangnhap,maca);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `KIEM_TRA_CO_HOA_DON` (`ban` CHAR(8))  begin
	select mahoadon from trangthai where trangthai.ban=ban;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `KIEM_TRA_VAI_TRO` (`tendangnhap` CHAR(50))  begin
	select vaitro from nguoidung where nguoidung.tendangnhap=tendangnhap;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_CHI_TIET_HOA_DON_NHAP` (`mhd` CHAR(8))  BEGIN
	SELECT * FROM chitiethoadonnhap WHERE chitiethoadonnhap.mahoadonnhap=mhd;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_DON_VI_DON_GIA` (`tennguyenlieu` VARCHAR(50))  begin
	select donvi,dongia from kho where kho.tennguyenlieu=tennguyenlieu;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_HOA_DON` (`mahoadon` CHAR(8))  begin
	select * from chitiethoadon where chitiethoadon.mahoadon=mahoadon;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_KHO` ()  begin
	select * from kho order by tennguyenlieu asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_TEN_NGUYEN_LIEU` ()  begin
	select tennguyenlieu from kho order by tennguyenlieu asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_TONG_TIEN` (`mahoadon` CHAR(8))  begin
    select tongtien from hoadon where hoadon.mahoadon=mahoadon;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_TRANG_THAI` ()  BEGIN
	SELECT ban FROM trangthai ORDER BY ban ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_TRANG_THAI_CA` (`tendangnhap` CHAR(50))  begin
    select * from trangthaica where trangthaica.tendangnhap=tendangnhap;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_TRANG_THAI_HOA_DON_NHAP` (`mhd` CHAR(8))  BEGIN
	SELECT thanhtoan FROM hoadonnhap WHERE hoadonnhap.mahoadonnhap=mhd;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `THEM_NGUYEN_LIEU_MOI` (`tennguyenlieu` VARCHAR(50), `soluong` INT, `donvi` VARCHAR(20), `dongia` INT)  begin
	insert into kho values (tennguyenlieu,soluong,donvi,dongia);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TINH_TIEN` (`mahoadon` CHAR(8))  begin
	UPDATE hoadon SET thanhtoan=1 WHERE hoadon.mahoadon=mahoadon;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TINH_TIEN_VA_XOA_HOA_DON` (`ban` CHAR(8))  begin
    DELETE FROM trangthai WHERE trangthai.ban=ban;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UPDATE_HOA_DON` (`mahoadon` CHAR(8), `tongtien` INT)  begin
    UPDATE hoadon SET hoadon.tongtien=hoadon.tongtien+tongtien WHERE hoadon.mahoadon=mahoadon;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UP_CHI_TIET_HOA_DON` (`mahoadon` CHAR(8), `tenmon` VARCHAR(50), `dongia` INT, `soluong` INT, `thanhtien` INT)  begin
    insert into chitiethoadon VALUES (mahoadon,tenmon,dongia,soluong,thanhtien);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UP_CHI_TIET_HOA_DON_NHAP` (`mahoadonnhap` CHAR(8), `tennguyenlieu` VARCHAR(50), `soluong` INT, `donvi` VARCHAR(20), `dongia` INT, `thanhtien` INT)  begin
    insert into chitiethoadonnhap VALUES (mahoadonnhap,tennguyenlieu,soluong,donvi,dongia,thanhtien);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UP_HOA_DON` (`mahoadon` CHAR(8), `tongtien` INT, `ngay` DATE, `thanhtoan` INT, `tendangnhap` CHAR(50))  begin
    insert into hoadon VALUES (mahoadon,tongtien,ngay,thanhtoan,tendangnhap);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UP_HOA_DON_NHAP` (`mahoadonnhap` CHAR(8), `tongtien` INT, `ngay` DATE, `thanhtoan` INT, `tendangnhap` CHAR(50))  begin
    insert into hoadonnhap VALUES (mahoadonnhap,tongtien,ngay,thanhtoan,tendangnhap);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_CA` (`maca` CHAR(8))  begin
	delete from ghichuca where ghichuca.maca=maca;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_CHI_TIET_HOA_DON` (`mahoadon` CHAR(8))  begin
    DELETE FROM chitiethoadon WHERE chitiethoadon.mahoadon=mahoadon;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_CHI_TIET_HOA_DON_NHAP` (`mahoadonnhap` CHAR(8))  begin
	delete from chitiethoadonnhap where chitiethoadonnhap.mahoadonnhap=mahoadonnhap;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_HOA_DON` (`mahoadon` CHAR(8))  begin
    DELETE FROM hoadon WHERE hoadon.mahoadon=mahoadon;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_HOA_DON_NHAP` (`mahoadonnhap` CHAR(8))  begin
	delete from hoadonnhap where hoadonnhap.mahoadonnhap=mahoadonnhap;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_HOA_DON_TAI_BAN` (`ban` CHAR(8))  BEGIN
	DELETE FROM trangthai WHERE trangthai.ban=ban;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_NGUYEN_LIEU` (`tennguyenlieu` VARCHAR(50))  begin
    DELETE FROM kho WHERE kho.tennguyenlieu=tennguyenlieu;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_TRANG_THAI_CA` (`tendangnhap` CHAR(50))  begin
    delete from trangthaica where trangthaica.tendangnhap=tendangnhap;
end$$

--
-- Các hàm
--
CREATE DEFINER=`root`@`localhost` FUNCTION `CO_HD_CHUA` (`b` CHAR(8)) RETURNS TINYINT(1) READS SQL DATA
BEGIN
	DECLARE kq BOOLEAN;
    DECLARE kt CHAR(8);
	select ban INTO kt from trangthai where trangthai.ban=b;
    if(kt=b) THEN SET kq=1;
    else set kq=0;
    end if;
    return kq;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `KT_DANG_NHAP` (`usr` CHAR(50), `pass` CHAR(50)) RETURNS TINYINT(1) READS SQL DATA
BEGIN
	DECLARE kq BOOLEAN;
    DECLARE nd CHAR(50);
	SELECT tendangnhap INTO nd FROM nguoidung 
    WHERE nguoidung.tendangnhap=usr AND nguoidung.matkhau=pass;
    IF(nd=usr) THEN 
		SET kq=1;
    ELSE 
		SET kq=0;
    END IF;
    RETURN  kq;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `LAY_GIA_TIEN` (`tenmon` VARCHAR(50)) RETURNS INT(11) READS SQL DATA
BEGIN
	DECLARE KQ INT;
    SELECT giatien INTO KQ FROM thucdon WHERE thucdon.tenmon=tenmon;
    RETURN KQ;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `LAY_HO_TEN` (`usr` CHAR(50)) RETURNS VARCHAR(50) CHARSET utf8mb4 READS SQL DATA
BEGIN
	DECLARE nam VARCHAR(50);
    SELECT hoten INTO nam FROM nguoidung WHERE nguoidung.tendangnhap=usr;
    RETURN nam;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `LAY_MA_HOA_DON` (`ban` CHAR(8)) RETURNS CHAR(8) CHARSET utf8mb4 READS SQL DATA
BEGIN
	DECLARE KQ CHAR(8);
    SELECT mahoadon INTO KQ FROM trangthai WHERE trangthai.ban=ban;
    RETURN KQ;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `mahoadon` char(8) DEFAULT NULL,
  `tenmon` varchar(50) DEFAULT NULL,
  `dongia` int(11) DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `thanhtien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadonnhap`
--

CREATE TABLE `chitiethoadonnhap` (
  `mahoadonnhap` char(8) DEFAULT NULL,
  `tennguyenlieu` varchar(50) DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `donvi` varchar(20) DEFAULT NULL,
  `dongia` int(11) DEFAULT NULL,
  `thanhtien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ghichuca`
--

CREATE TABLE `ghichuca` (
  `maca` char(8) NOT NULL,
  `tendangnhap` char(50) DEFAULT NULL,
  `ngay` date DEFAULT NULL,
  `ghichunhan` varchar(200) DEFAULT NULL,
  `ghichudong` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahoadon` char(8) NOT NULL,
  `tongtien` int(11) DEFAULT NULL,
  `ngay` date DEFAULT NULL,
  `thanhtoan` int(1) DEFAULT NULL,
  `tendangnhap` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadonnhap`
--

CREATE TABLE `hoadonnhap` (
  `mahoadonnhap` char(8) NOT NULL,
  `tongtien` int(11) DEFAULT NULL,
  `ngay` date DEFAULT NULL,
  `thanhtoan` int(1) DEFAULT NULL,
  `tendangnhap` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `kho`
--

CREATE TABLE `kho` (
  `tennguyenlieu` varchar(50) NOT NULL,
  `soluong` int(11) DEFAULT NULL,
  `donvi` varchar(20) DEFAULT NULL,
  `dongia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `tendangnhap` char(50) NOT NULL,
  `matkhau` char(50) DEFAULT NULL,
  `hoten` varchar(50) DEFAULT NULL,
  `vaitro` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nguoidung`
--

INSERT INTO `nguoidung` (`tendangnhap`, `matkhau`, `hoten`, `vaitro`) VALUES
('admin', '1', 'Vo Ad Min', 'Admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhatky`
--

CREATE TABLE `nhatky` (
  `ngay` char(50) DEFAULT NULL,
  `noidung` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thucdon`
--

CREATE TABLE `thucdon` (
  `tenMon` varchar(50) NOT NULL,
  `giaTien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `trangthai`
--

CREATE TABLE `trangthai` (
  `ban` char(8) NOT NULL,
  `mahoadon` char(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `trangthaica`
--

CREATE TABLE `trangthaica` (
  `tendangnhap` char(50) NOT NULL,
  `maca` char(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `mahoadon` (`mahoadon`);

--
-- Chỉ mục cho bảng `chitiethoadonnhap`
--
ALTER TABLE `chitiethoadonnhap`
  ADD KEY `mahoadonnhap` (`mahoadonnhap`);

--
-- Chỉ mục cho bảng `ghichuca`
--
ALTER TABLE `ghichuca`
  ADD PRIMARY KEY (`maca`),
  ADD KEY `tendangnhap` (`tendangnhap`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahoadon`),
  ADD KEY `tendangnhap` (`tendangnhap`);

--
-- Chỉ mục cho bảng `hoadonnhap`
--
ALTER TABLE `hoadonnhap`
  ADD PRIMARY KEY (`mahoadonnhap`),
  ADD KEY `tendangnhap` (`tendangnhap`);

--
-- Chỉ mục cho bảng `kho`
--
ALTER TABLE `kho`
  ADD PRIMARY KEY (`tennguyenlieu`);

--
-- Chỉ mục cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`tendangnhap`);

--
-- Chỉ mục cho bảng `thucdon`
--
ALTER TABLE `thucdon`
  ADD PRIMARY KEY (`tenMon`);

--
-- Chỉ mục cho bảng `trangthai`
--
ALTER TABLE `trangthai`
  ADD PRIMARY KEY (`ban`),
  ADD KEY `kn_mahoadon_idx` (`mahoadon`);

--
-- Chỉ mục cho bảng `trangthaica`
--
ALTER TABLE `trangthaica`
  ADD PRIMARY KEY (`tendangnhap`),
  ADD KEY `trangthaica_pk1_idx` (`maca`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`mahoadon`) REFERENCES `hoadon` (`mahoadon`);

--
-- Các ràng buộc cho bảng `chitiethoadonnhap`
--
ALTER TABLE `chitiethoadonnhap`
  ADD CONSTRAINT `chitiethoadonnhap_ibfk_1` FOREIGN KEY (`mahoadonnhap`) REFERENCES `hoadonnhap` (`mahoadonnhap`);

--
-- Các ràng buộc cho bảng `ghichuca`
--
ALTER TABLE `ghichuca`
  ADD CONSTRAINT `ghichuca_ibfk_1` FOREIGN KEY (`tendangnhap`) REFERENCES `nguoidung` (`tendangnhap`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`tendangnhap`) REFERENCES `nguoidung` (`tendangnhap`);

--
-- Các ràng buộc cho bảng `hoadonnhap`
--
ALTER TABLE `hoadonnhap`
  ADD CONSTRAINT `hoadonnhap_ibfk_1` FOREIGN KEY (`tendangnhap`) REFERENCES `nguoidung` (`tendangnhap`);

--
-- Các ràng buộc cho bảng `trangthai`
--
ALTER TABLE `trangthai`
  ADD CONSTRAINT `trangthan_pk1` FOREIGN KEY (`mahoadon`) REFERENCES `hoadon` (`mahoadon`);

--
-- Các ràng buộc cho bảng `trangthaica`
--
ALTER TABLE `trangthaica`
  ADD CONSTRAINT `trangthaica_pk1` FOREIGN KEY (`maca`) REFERENCES `ghichuca` (`maca`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
