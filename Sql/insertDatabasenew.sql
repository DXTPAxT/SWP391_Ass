
-- Insert data into Roles
INSERT INTO Roles (RoleName) VALUES 
('Admin'),
('Sale'),
('Customer'),
('Shipper');

-- Insert data into Users
INSERT INTO Users (RoleID, FullName, Email, PhoneNumber, PasswordHash, CreatedAt, Status) VALUES
(1, 'Alice Nguyen', 'alice@domain.com', '0912345678', 'hashedpassword123', GETDATE(), 1),
(2, 'Bob Tran', 'bob@domain.com', '0987654321', 'securepass456', GETDATE(), 1),
(3, 'Charlie Pham', 'charlie@domain.com', '0909090909', 'pass789secure', GETDATE(), 1),
(4, 'Charlie Pham2', 'charlie@doma.com', '0909090908', 'pass789secure', GETDATE(), 1);

INSERT INTO CustomerInfo (UserID, Address) VALUES
(3, '789 Nguyen Trai, DN');

INSERT INTO StaffInfo (UserID, StartedDate, EndDate) VALUES
(2, '2023-01-01', '2025-12-31'),
(4, '2023-01-01', '2025-12-31');

INSERT INTO Components (ComponentName, Quantity, Status) VALUES
('PC', 50, 1),
('MainBoard', 120, 1),
('CPU', 100, 1),
('GPU', 80, 1),
('RAM', 150, 1),
('SSD', 90, 1),
('Case', 70, 1);

-- Insert data into Brands
INSERT INTO Brands (BrandName, Quantity, Status) VALUES
('ASUS', 200, 1),
('MSI', 180, 1),
('GIGABYTE', 160, 1),
('Intel', 220, 1),
('AMD', 210, 1),
('NVIDIA', 190, 1),
('Corsair', 170, 1),
('Kingston', 150, 1),
('Samsung', 230, 1),
('Cooler Master', 140, 1);

-- Insert data into BrandComs
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (7, 1, 63);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (2, 4, 85);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (3, 9, 83);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (1, 4, 45);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (7, 10, 93);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (4, 9, 83);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (6, 10, 56);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (5, 1, 94);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (5, 3, 33);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (7, 10, 59);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (3, 7, 39);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (6, 8, 52);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (3, 6, 16);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (4, 7, 54);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (6, 2, 42);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (5, 7, 39);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (2, 10, 26);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (3, 1, 86);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (4, 1, 19);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (7, 6, 57);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (7, 7, 25);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (3, 6, 36);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (7, 1, 85);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (7, 10, 27);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (4, 3, 40);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (1, 8, 40);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (1, 3, 59);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (2, 7, 72);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (5, 1, 61);
INSERT INTO BrandComs (ComponentID, BrandID, Quantity) VALUES (6, 4, 71);


-- Insert data into Categories
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_001', 12, 16, 1582000, N'This is the description for Category_001.', 0, N'https://example.com/images/category_001.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_002', 1, 24, 222000, N'This is the description for Category_002.', 2, N'https://example.com/images/category_002.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_003', 2, 39, 338000, N'This is the description for Category_003.', 2, N'https://example.com/images/category_003.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_004', 27, 30, 2609000, N'This is the description for Category_004.', 1, N'https://example.com/images/category_004.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_005', 14, 6, 161000, N'This is the description for Category_005.', 2, N'https://example.com/images/category_005.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_006', 6, 27, 2966000, N'This is the description for Category_006.', 2, N'https://example.com/images/category_006.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_007', 2, 24, 1255000, N'This is the description for Category_007.', 1, N'https://example.com/images/category_007.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_008', 24, 6, 1052000, N'This is the description for Category_008.', 2, N'https://example.com/images/category_008.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_009', 1, 28, 2566000, N'This is the description for Category_009.', 1, N'https://example.com/images/category_009.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_010', 22, 25, 918000, N'This is the description for Category_010.', 1, N'https://example.com/images/category_010.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_011', 4, 33, 1126000, N'This is the description for Category_011.', 2, N'https://example.com/images/category_011.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_012', 2, 10, 2966000, N'This is the description for Category_012.', 0, N'https://example.com/images/category_012.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_013', 11, 24, 896000, N'This is the description for Category_013.', 1, N'https://example.com/images/category_013.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_014', 4, 40, 1936000, N'This is the description for Category_014.', 1, N'https://example.com/images/category_014.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_015', 13, 16, 1205000, N'This is the description for Category_015.', 1, N'https://example.com/images/category_015.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_016', 1, 6, 1265000, N'This is the description for Category_016.', 1, N'https://example.com/images/category_016.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_017', 3, 36, 2780000, N'This is the description for Category_017.', 2, N'https://example.com/images/category_017.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_018', 26, 31, 461000, N'This is the description for Category_018.', 1, N'https://example.com/images/category_018.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_019', 3, 38, 151000, N'This is the description for Category_019.', 2, N'https://example.com/images/category_019.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_020', 19, 25, 2175000, N'This is the description for Category_020.', 1, N'https://example.com/images/category_020.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_021', 20, 40, 1566000, N'This is the description for Category_021.', 0, N'https://example.com/images/category_021.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_022', 17, 14, 1409000, N'This is the description for Category_022.', 0, N'https://example.com/images/category_022.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_023', 18, 40, 721000, N'This is the description for Category_023.', 0, N'https://example.com/images/category_023.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_024', 26, 50, 365000, N'This is the description for Category_024.', 1, N'https://example.com/images/category_024.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_025', 21, 23, 1413000, N'This is the description for Category_025.', 2, N'https://example.com/images/category_025.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_026', 2, 10, 1490000, N'This is the description for Category_026.', 2, N'https://example.com/images/category_026.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_027', 25, 7, 2359000, N'This is the description for Category_027.', 0, N'https://example.com/images/category_027.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_028', 1, 31, 524000, N'This is the description for Category_028.', 2, N'https://example.com/images/category_028.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_029', 2, 36, 2410000, N'This is the description for Category_029.', 2, N'https://example.com/images/category_029.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_030', 14, 25, 603000, N'This is the description for Category_030.', 0, N'https://example.com/images/category_030.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_031', 17, 12, 2423000, N'This is the description for Category_031.', 2, N'https://example.com/images/category_031.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_032', 24, 43, 295000, N'This is the description for Category_032.', 1, N'https://example.com/images/category_032.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_033', 14, 34, 625000, N'This is the description for Category_033.', 0, N'https://example.com/images/category_033.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_034', 15, 21, 652000, N'This is the description for Category_034.', 0, N'https://example.com/images/category_034.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_035', 2, 46, 2904000, N'This is the description for Category_035.', 1, N'https://example.com/images/category_035.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_036', 19, 36, 125000, N'This is the description for Category_036.', 2, N'https://example.com/images/category_036.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_037', 18, 50, 2515000, N'This is the description for Category_037.', 0, N'https://example.com/images/category_037.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_038', 14, 23, 1042000, N'This is the description for Category_038.', 0, N'https://example.com/images/category_038.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_039', 15, 34, 464000, N'This is the description for Category_039.', 1, N'https://example.com/images/category_039.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_040', 6, 36, 2826000, N'This is the description for Category_040.', 0, N'https://example.com/images/category_040.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_041', 26, 16, 1649000, N'This is the description for Category_041.', 2, N'https://example.com/images/category_041.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_042', 9, 37, 1387000, N'This is the description for Category_042.', 2, N'https://example.com/images/category_042.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_043', 3, 43, 2050000, N'This is the description for Category_043.', 1, N'https://example.com/images/category_043.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_044', 10, 43, 1545000, N'This is the description for Category_044.', 0, N'https://example.com/images/category_044.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_045', 25, 33, 355000, N'This is the description for Category_045.', 2, N'https://example.com/images/category_045.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_046', 9, 42, 2926000, N'This is the description for Category_046.', 1, N'https://example.com/images/category_046.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_047', 12, 47, 895000, N'This is the description for Category_047.', 1, N'https://example.com/images/category_047.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_048', 4, 23, 2865000, N'This is the description for Category_048.', 1, N'https://example.com/images/category_048.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_049', 14, 5, 574000, N'This is the description for Category_049.', 0, N'https://example.com/images/category_049.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_050', 4, 23, 941000, N'This is the description for Category_050.', 1, N'https://example.com/images/category_050.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_051', 21, 12, 1153000, N'This is the description for Category_051.', 2, N'https://example.com/images/category_051.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_052', 9, 21, 1735000, N'This is the description for Category_052.', 2, N'https://example.com/images/category_052.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_053', 11, 49, 2976000, N'This is the description for Category_053.', 1, N'https://example.com/images/category_053.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_054', 20, 6, 2754000, N'This is the description for Category_054.', 1, N'https://example.com/images/category_054.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_055', 24, 32, 409000, N'This is the description for Category_055.', 1, N'https://example.com/images/category_055.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_056', 1, 34, 107000, N'This is the description for Category_056.', 2, N'https://example.com/images/category_056.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_057', 20, 15, 1981000, N'This is the description for Category_057.', 1, N'https://example.com/images/category_057.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_058', 20, 34, 457000, N'This is the description for Category_058.', 1, N'https://example.com/images/category_058.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_059', 11, 35, 1070000, N'This is the description for Category_059.', 0, N'https://example.com/images/category_059.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_060', 14, 25, 301000, N'This is the description for Category_060.', 2, N'https://example.com/images/category_060.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_061', 27, 41, 2980000, N'This is the description for Category_061.', 2, N'https://example.com/images/category_061.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_062', 12, 43, 2120000, N'This is the description for Category_062.', 0, N'https://example.com/images/category_062.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_063', 25, 20, 1489000, N'This is the description for Category_063.', 1, N'https://example.com/images/category_063.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_064', 16, 47, 1742000, N'This is the description for Category_064.', 1, N'https://example.com/images/category_064.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_065', 23, 13, 424000, N'This is the description for Category_065.', 0, N'https://example.com/images/category_065.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_066', 4, 32, 2476000, N'This is the description for Category_066.', 0, N'https://example.com/images/category_066.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_067', 23, 8, 1130000, N'This is the description for Category_067.', 0, N'https://example.com/images/category_067.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_068', 23, 32, 1825000, N'This is the description for Category_068.', 0, N'https://example.com/images/category_068.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_069', 17, 9, 223000, N'This is the description for Category_069.', 1, N'https://example.com/images/category_069.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_070', 9, 9, 2943000, N'This is the description for Category_070.', 2, N'https://example.com/images/category_070.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_071', 29, 32, 1681000, N'This is the description for Category_071.', 0, N'https://example.com/images/category_071.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_072', 26, 42, 1806000, N'This is the description for Category_072.', 2, N'https://example.com/images/category_072.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_073', 20, 14, 2052000, N'This is the description for Category_073.', 0, N'https://example.com/images/category_073.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_074', 10, 22, 2748000, N'This is the description for Category_074.', 1, N'https://example.com/images/category_074.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_075', 18, 8, 1409000, N'This is the description for Category_075.', 0, N'https://example.com/images/category_075.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_076', 6, 41, 823000, N'This is the description for Category_076.', 2, N'https://example.com/images/category_076.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_077', 30, 6, 497000, N'This is the description for Category_077.', 2, N'https://example.com/images/category_077.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_078', 20, 27, 631000, N'This is the description for Category_078.', 0, N'https://example.com/images/category_078.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_079', 7, 17, 2756000, N'This is the description for Category_079.', 2, N'https://example.com/images/category_079.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_080', 26, 37, 1449000, N'This is the description for Category_080.', 2, N'https://example.com/images/category_080.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_081', 3, 12, 1313000, N'This is the description for Category_081.', 2, N'https://example.com/images/category_081.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_082', 25, 49, 1001000, N'This is the description for Category_082.', 1, N'https://example.com/images/category_082.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_083', 18, 6, 2454000, N'This is the description for Category_083.', 1, N'https://example.com/images/category_083.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_084', 15, 13, 1298000, N'This is the description for Category_084.', 1, N'https://example.com/images/category_084.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_085', 25, 25, 832000, N'This is the description for Category_085.', 2, N'https://example.com/images/category_085.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_086', 9, 29, 295000, N'This is the description for Category_086.', 0, N'https://example.com/images/category_086.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_087', 7, 49, 2075000, N'This is the description for Category_087.', 1, N'https://example.com/images/category_087.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_088', 6, 32, 671000, N'This is the description for Category_088.', 0, N'https://example.com/images/category_088.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_089', 5, 13, 2860000, N'This is the description for Category_089.', 1, N'https://example.com/images/category_089.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_090', 27, 14, 880000, N'This is the description for Category_090.', 1, N'https://example.com/images/category_090.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_091', 19, 20, 1321000, N'This is the description for Category_091.', 1, N'https://example.com/images/category_091.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_092', 3, 24, 2546000, N'This is the description for Category_092.', 0, N'https://example.com/images/category_092.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_093', 16, 14, 2126000, N'This is the description for Category_093.', 2, N'https://example.com/images/category_093.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_094', 20, 24, 201000, N'This is the description for Category_094.', 2, N'https://example.com/images/category_094.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_095', 6, 17, 2532000, N'This is the description for Category_095.', 2, N'https://example.com/images/category_095.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_096', 19, 40, 2343000, N'This is the description for Category_096.', 2, N'https://example.com/images/category_096.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_097', 28, 33, 1093000, N'This is the description for Category_097.', 2, N'https://example.com/images/category_097.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_098', 30, 23, 2696000, N'This is the description for Category_098.', 2, N'https://example.com/images/category_098.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_099', 27, 7, 187000, N'This is the description for Category_099.', 2, N'https://example.com/images/category_099.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_100', 10, 11, 2351000, N'This is the description for Category_100.', 1, N'https://example.com/images/category_100.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_101', 28, 10, 997000, N'This is the description for Category_101.', 1, N'https://example.com/images/category_101.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_102', 23, 28, 2087000, N'This is the description for Category_102.', 2, N'https://example.com/images/category_102.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_103', 8, 25, 775000, N'This is the description for Category_103.', 1, N'https://example.com/images/category_103.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_104', 30, 41, 1222000, N'This is the description for Category_104.', 0, N'https://example.com/images/category_104.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_105', 22, 36, 2570000, N'This is the description for Category_105.', 2, N'https://example.com/images/category_105.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_106', 4, 10, 2043000, N'This is the description for Category_106.', 2, N'https://example.com/images/category_106.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_107', 28, 20, 1096000, N'This is the description for Category_107.', 2, N'https://example.com/images/category_107.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_108', 6, 18, 1469000, N'This is the description for Category_108.', 2, N'https://example.com/images/category_108.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_109', 3, 42, 2262000, N'This is the description for Category_109.', 0, N'https://example.com/images/category_109.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_110', 19, 20, 1302000, N'This is the description for Category_110.', 1, N'https://example.com/images/category_110.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_111', 18, 36, 2597000, N'This is the description for Category_111.', 0, N'https://example.com/images/category_111.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_112', 21, 49, 1553000, N'This is the description for Category_112.', 0, N'https://example.com/images/category_112.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_113', 10, 28, 2790000, N'This is the description for Category_113.', 1, N'https://example.com/images/category_113.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_114', 29, 8, 1824000, N'This is the description for Category_114.', 0, N'https://example.com/images/category_114.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_115', 16, 34, 771000, N'This is the description for Category_115.', 0, N'https://example.com/images/category_115.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_116', 24, 49, 402000, N'This is the description for Category_116.', 0, N'https://example.com/images/category_116.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_117', 25, 31, 2535000, N'This is the description for Category_117.', 1, N'https://example.com/images/category_117.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_118', 11, 29, 144000, N'This is the description for Category_118.', 1, N'https://example.com/images/category_118.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_119', 18, 12, 2463000, N'This is the description for Category_119.', 1, N'https://example.com/images/category_119.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_120', 20, 23, 1079000, N'This is the description for Category_120.', 2, N'https://example.com/images/category_120.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_121', 17, 37, 312000, N'This is the description for Category_121.', 1, N'https://example.com/images/category_121.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_122', 3, 26, 1324000, N'This is the description for Category_122.', 2, N'https://example.com/images/category_122.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_123', 21, 44, 169000, N'This is the description for Category_123.', 0, N'https://example.com/images/category_123.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_124', 16, 35, 600000, N'This is the description for Category_124.', 1, N'https://example.com/images/category_124.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_125', 2, 48, 350000, N'This is the description for Category_125.', 1, N'https://example.com/images/category_125.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_126', 7, 30, 1195000, N'This is the description for Category_126.', 1, N'https://example.com/images/category_126.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_127', 6, 25, 1797000, N'This is the description for Category_127.', 0, N'https://example.com/images/category_127.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_128', 14, 25, 424000, N'This is the description for Category_128.', 0, N'https://example.com/images/category_128.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_129', 24, 23, 1864000, N'This is the description for Category_129.', 0, N'https://example.com/images/category_129.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_130', 19, 44, 2807000, N'This is the description for Category_130.', 1, N'https://example.com/images/category_130.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_131', 11, 16, 1664000, N'This is the description for Category_131.', 1, N'https://example.com/images/category_131.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_132', 29, 43, 1663000, N'This is the description for Category_132.', 2, N'https://example.com/images/category_132.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_133', 15, 33, 574000, N'This is the description for Category_133.', 1, N'https://example.com/images/category_133.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_134', 10, 18, 415000, N'This is the description for Category_134.', 1, N'https://example.com/images/category_134.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_135', 23, 14, 2958000, N'This is the description for Category_135.', 2, N'https://example.com/images/category_135.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_136', 18, 17, 1791000, N'This is the description for Category_136.', 2, N'https://example.com/images/category_136.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_137', 13, 20, 1706000, N'This is the description for Category_137.', 0, N'https://example.com/images/category_137.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_138', 12, 9, 1724000, N'This is the description for Category_138.', 2, N'https://example.com/images/category_138.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_139', 1, 44, 2563000, N'This is the description for Category_139.', 0, N'https://example.com/images/category_139.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_140', 1, 40, 1561000, N'This is the description for Category_140.', 2, N'https://example.com/images/category_140.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_141', 29, 48, 1016000, N'This is the description for Category_141.', 2, N'https://example.com/images/category_141.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_142', 26, 15, 2968000, N'This is the description for Category_142.', 1, N'https://example.com/images/category_142.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_143', 27, 19, 584000, N'This is the description for Category_143.', 2, N'https://example.com/images/category_143.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_144', 2, 45, 1279000, N'This is the description for Category_144.', 1, N'https://example.com/images/category_144.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_145', 15, 9, 843000, N'This is the description for Category_145.', 0, N'https://example.com/images/category_145.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_146', 14, 19, 433000, N'This is the description for Category_146.', 1, N'https://example.com/images/category_146.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_147', 30, 12, 1232000, N'This is the description for Category_147.', 1, N'https://example.com/images/category_147.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_148', 7, 43, 656000, N'This is the description for Category_148.', 0, N'https://example.com/images/category_148.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_149', 12, 42, 2176000, N'This is the description for Category_149.', 0, N'https://example.com/images/category_149.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_150', 16, 31, 377000, N'This is the description for Category_150.', 2, N'https://example.com/images/category_150.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_151', 19, 7, 389000, N'This is the description for Category_151.', 2, N'https://example.com/images/category_151.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_152', 1, 10, 924000, N'This is the description for Category_152.', 2, N'https://example.com/images/category_152.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_153', 9, 45, 1667000, N'This is the description for Category_153.', 1, N'https://example.com/images/category_153.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_154', 3, 20, 1212000, N'This is the description for Category_154.', 1, N'https://example.com/images/category_154.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_155', 5, 18, 2065000, N'This is the description for Category_155.', 2, N'https://example.com/images/category_155.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_156', 4, 17, 164000, N'This is the description for Category_156.', 0, N'https://example.com/images/category_156.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_157', 9, 49, 2360000, N'This is the description for Category_157.', 0, N'https://example.com/images/category_157.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_158', 18, 43, 826000, N'This is the description for Category_158.', 2, N'https://example.com/images/category_158.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_159', 11, 47, 472000, N'This is the description for Category_159.', 1, N'https://example.com/images/category_159.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_160', 13, 49, 1705000, N'This is the description for Category_160.', 2, N'https://example.com/images/category_160.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_161', 13, 10, 352000, N'This is the description for Category_161.', 0, N'https://example.com/images/category_161.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_162', 1, 6, 2409000, N'This is the description for Category_162.', 1, N'https://example.com/images/category_162.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_163', 17, 5, 2119000, N'This is the description for Category_163.', 0, N'https://example.com/images/category_163.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_164', 28, 11, 1790000, N'This is the description for Category_164.', 2, N'https://example.com/images/category_164.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_165', 19, 27, 588000, N'This is the description for Category_165.', 1, N'https://example.com/images/category_165.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_166', 13, 27, 1897000, N'This is the description for Category_166.', 1, N'https://example.com/images/category_166.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_167', 5, 49, 1837000, N'This is the description for Category_167.', 0, N'https://example.com/images/category_167.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_168', 20, 22, 1321000, N'This is the description for Category_168.', 0, N'https://example.com/images/category_168.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_169', 13, 23, 192000, N'This is the description for Category_169.', 0, N'https://example.com/images/category_169.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_170', 15, 45, 687000, N'This is the description for Category_170.', 1, N'https://example.com/images/category_170.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_171', 12, 36, 1529000, N'This is the description for Category_171.', 1, N'https://example.com/images/category_171.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_172', 3, 19, 1079000, N'This is the description for Category_172.', 0, N'https://example.com/images/category_172.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_173', 23, 46, 832000, N'This is the description for Category_173.', 2, N'https://example.com/images/category_173.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_174', 15, 37, 2821000, N'This is the description for Category_174.', 2, N'https://example.com/images/category_174.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_175', 22, 13, 2708000, N'This is the description for Category_175.', 0, N'https://example.com/images/category_175.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_176', 29, 31, 1002000, N'This is the description for Category_176.', 1, N'https://example.com/images/category_176.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_177', 27, 26, 1819000, N'This is the description for Category_177.', 2, N'https://example.com/images/category_177.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_178', 20, 41, 1212000, N'This is the description for Category_178.', 2, N'https://example.com/images/category_178.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_179', 11, 39, 950000, N'This is the description for Category_179.', 2, N'https://example.com/images/category_179.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_180', 7, 42, 2637000, N'This is the description for Category_180.', 0, N'https://example.com/images/category_180.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_181', 1, 23, 1624000, N'This is the description for Category_181.', 2, N'https://example.com/images/category_181.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_182', 18, 37, 471000, N'This is the description for Category_182.', 1, N'https://example.com/images/category_182.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_183', 19, 25, 2363000, N'This is the description for Category_183.', 0, N'https://example.com/images/category_183.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_184', 6, 10, 721000, N'This is the description for Category_184.', 1, N'https://example.com/images/category_184.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_185', 29, 20, 1269000, N'This is the description for Category_185.', 1, N'https://example.com/images/category_185.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_186', 3, 13, 891000, N'This is the description for Category_186.', 1, N'https://example.com/images/category_186.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_187', 14, 26, 257000, N'This is the description for Category_187.', 0, N'https://example.com/images/category_187.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_188', 25, 26, 2857000, N'This is the description for Category_188.', 0, N'https://example.com/images/category_188.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_189', 9, 15, 2678000, N'This is the description for Category_189.', 0, N'https://example.com/images/category_189.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_190', 18, 36, 2007000, N'This is the description for Category_190.', 1, N'https://example.com/images/category_190.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_191', 10, 44, 2984000, N'This is the description for Category_191.', 2, N'https://example.com/images/category_191.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_192', 9, 37, 2866000, N'This is the description for Category_192.', 2, N'https://example.com/images/category_192.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_193', 26, 33, 641000, N'This is the description for Category_193.', 0, N'https://example.com/images/category_193.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_194', 5, 19, 2704000, N'This is the description for Category_194.', 0, N'https://example.com/images/category_194.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_195', 26, 40, 558000, N'This is the description for Category_195.', 2, N'https://example.com/images/category_195.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_196', 27, 7, 628000, N'This is the description for Category_196.', 1, N'https://example.com/images/category_196.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_197', 18, 7, 2727000, N'This is the description for Category_197.', 2, N'https://example.com/images/category_197.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_198', 29, 42, 1456000, N'This is the description for Category_198.', 2, N'https://example.com/images/category_198.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_199', 12, 41, 1032000, N'This is the description for Category_199.', 1, N'https://example.com/images/category_199.jpg');
INSERT INTO Categories (CategoryName, BrandComID, Quantity, Price, Description, Status, ImageURL) VALUES (N'Category_200', 16, 49, 2403000, N'This is the description for Category_200.', 0, N'https://example.com/images/category_200.jpg');

INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM001', 32, '2024-11-06 05:30:02', 34, 571000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM002', 127, '2024-07-29 05:30:02', 50, 2144000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM003', 37, '2024-10-18 05:30:02', 27, 1769000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM004', 76, '2025-03-26 05:30:02', 31, 349000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM005', 103, '2024-07-28 05:30:02', 34, 2792000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM006', 123, '2025-05-15 05:30:02', 36, 2969000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM007', 83, '2024-11-10 05:30:02', 42, 1445000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM008', 43, '2024-07-19 05:30:02', 10, 2164000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM009', 71, '2024-07-02 05:30:02', 43, 1115000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM010', 164, '2025-03-17 05:30:02', 33, 612000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM011', 84, '2025-05-18 05:30:02', 45, 478000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM012', 24, '2024-10-11 05:30:02', 37, 1392000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM013', 169, '2024-07-04 05:30:02', 38, 2196000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM014', 6, '2025-04-18 05:30:02', 15, 1727000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM015', 162, '2024-09-26 05:30:02', 29, 1672000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM016', 38, '2024-11-01 05:30:02', 36, 1108000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM017', 76, '2024-09-23 05:30:02', 8, 1922000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM018', 60, '2025-01-29 05:30:02', 31, 1577000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM019', 35, '2024-08-12 05:30:02', 41, 1384000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM020', 69, '2025-04-20 05:30:02', 36, 2017000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM021', 135, '2024-08-29 05:30:02', 33, 2799000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM022', 29, '2024-12-31 05:30:02', 43, 2940000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM023', 10, '2024-12-09 05:30:02', 37, 1908000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM024', 101, '2024-11-17 05:30:02', 10, 1349000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM025', 95, '2025-05-05 05:30:02', 28, 1393000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM026', 88, '2025-02-20 05:30:02', 49, 348000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM027', 142, '2024-07-12 05:30:02', 19, 154000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM028', 76, '2025-01-28 05:30:02', 12, 601000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM029', 152, '2024-09-04 05:30:02', 16, 601000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM030', 164, '2025-03-24 05:30:02', 36, 1716000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM031', 177, '2024-11-28 05:30:02', 34, 2648000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM032', 12, '2024-08-28 05:30:02', 27, 199000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM033', 60, '2025-03-05 05:30:02', 21, 655000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM034', 82, '2025-04-03 05:30:02', 9, 2430000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM035', 189, '2025-05-07 05:30:02', 20, 2031000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM036', 54, '2024-11-16 05:30:02', 50, 2585000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM037', 77, '2024-10-06 05:30:02', 48, 2760000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM038', 3, '2024-08-26 05:30:02', 38, 695000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM039', 197, '2024-09-09 05:30:02', 19, 1269000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM040', 95, '2024-07-29 05:30:02', 17, 2418000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM041', 134, '2024-12-25 05:30:02', 20, 211000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM042', 47, '2025-05-28 05:30:02', 12, 2254000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM043', 150, '2024-10-11 05:30:02', 20, 756000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM044', 149, '2024-11-11 05:30:02', 8, 424000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM045', 132, '2025-04-14 05:30:02', 7, 2201000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM046', 93, '2024-12-27 05:30:02', 11, 1294000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM047', 79, '2025-02-06 05:30:02', 13, 2759000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM048', 51, '2024-07-02 05:30:02', 41, 1589000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM049', 107, '2024-09-02 05:30:02', 50, 2920000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM050', 169, '2025-01-20 05:30:02', 10, 368000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM051', 115, '2025-04-09 05:30:02', 26, 1825000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM052', 149, '2024-08-13 05:30:02', 32, 2500000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM053', 197, '2024-11-13 05:30:02', 37, 1327000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM054', 119, '2025-05-09 05:30:02', 28, 1008000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM055', 65, '2025-02-09 05:30:02', 33, 261000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM056', 31, '2024-12-03 05:30:02', 34, 743000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM057', 138, '2025-05-09 05:30:02', 24, 280000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM058', 49, '2024-07-30 05:30:02', 8, 1367000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM059', 46, '2024-06-09 05:30:02', 21, 1253000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM060', 1, '2025-02-22 05:30:02', 31, 927000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM061', 158, '2025-05-21 05:30:02', 47, 242000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM062', 57, '2025-01-15 05:30:02', 31, 2201000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM063', 56, '2025-01-20 05:30:02', 26, 1363000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM064', 24, '2024-07-15 05:30:02', 12, 759000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM065', 115, '2024-10-15 05:30:02', 36, 2415000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM066', 54, '2024-12-20 05:30:02', 28, 1658000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM067', 101, '2024-07-06 05:30:02', 22, 932000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM068', 76, '2024-09-18 05:30:02', 42, 1388000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM069', 70, '2025-02-11 05:30:02', 50, 2450000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM070', 173, '2025-03-03 05:30:02', 27, 1410000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM071', 72, '2024-08-08 05:30:02', 24, 2754000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM072', 173, '2024-09-07 05:30:02', 28, 892000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM073', 178, '2024-12-17 05:30:02', 43, 909000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM074', 125, '2024-12-02 05:30:02', 25, 223000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM075', 120, '2025-01-19 05:30:02', 49, 2943000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM076', 67, '2024-11-07 05:30:02', 23, 1858000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM077', 166, '2025-05-12 05:30:02', 47, 356000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM078', 97, '2025-01-21 05:30:02', 8, 2796000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM079', 49, '2024-08-15 05:30:02', 46, 1352000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM080', 14, '2025-02-26 05:30:02', 30, 1351000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM081', 163, '2025-02-07 05:30:02', 19, 1646000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM082', 46, '2024-09-03 05:30:02', 9, 2367000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM083', 133, '2025-01-14 05:30:02', 7, 1702000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM084', 102, '2025-02-11 05:30:02', 17, 1327000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM085', 24, '2025-05-06 05:30:02', 49, 1169000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM086', 131, '2024-10-15 05:30:02', 23, 2162000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM087', 49, '2025-05-30 05:30:02', 9, 1801000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM088', 141, '2024-12-14 05:30:02', 31, 1839000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM089', 85, '2024-11-04 05:30:02', 19, 2499000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM090', 82, '2025-02-07 05:30:02', 45, 2671000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM091', 181, '2025-01-25 05:30:02', 5, 1141000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM092', 69, '2024-09-13 05:30:02', 44, 2648000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM093', 172, '2024-06-21 05:30:02', 40, 1985000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM094', 85, '2025-04-19 05:30:02', 13, 1823000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM095', 132, '2024-10-17 05:30:02', 7, 223000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM096', 2, '2024-07-26 05:30:02', 34, 400000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM097', 169, '2024-08-21 05:30:02', 47, 334000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM098', 96, '2025-01-21 05:30:02', 36, 1979000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM099', 59, '2024-09-29 05:30:02', 49, 1239000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM100', 140, '2024-12-31 05:30:02', 10, 564000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM101', 8, '2024-07-14 05:30:02', 34, 1554000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM102', 181, '2024-10-19 05:30:02', 26, 2176000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM103', 175, '2024-09-03 05:30:02', 20, 2286000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM104', 110, '2024-10-31 05:30:02', 34, 575000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM105', 145, '2024-09-07 05:30:02', 17, 256000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM106', 48, '2024-09-28 05:30:02', 26, 241000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM107', 47, '2024-09-19 05:30:02', 36, 616000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM108', 81, '2024-07-07 05:30:02', 29, 414000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM109', 165, '2025-05-02 05:30:02', 9, 2188000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM110', 197, '2024-11-27 05:30:02', 17, 983000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM111', 87, '2025-05-13 05:30:02', 29, 765000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM112', 56, '2024-07-18 05:30:02', 34, 1529000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM113', 178, '2024-07-29 05:30:02', 34, 2242000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM114', 87, '2025-03-29 05:30:02', 35, 875000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM115', 50, '2025-02-05 05:30:02', 31, 2687000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM116', 31, '2024-11-30 05:30:02', 50, 1275000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM117', 78, '2025-03-07 05:30:02', 36, 1152000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM118', 74, '2024-07-05 05:30:02', 37, 1595000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM119', 28, '2024-09-27 05:30:02', 23, 1854000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM120', 101, '2025-02-26 05:30:02', 26, 1470000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM121', 56, '2025-06-07 05:30:02', 16, 2578000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM122', 81, '2024-08-12 05:30:02', 40, 1020000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM123', 37, '2024-12-26 05:30:02', 20, 2800000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM124', 30, '2025-05-20 05:30:02', 19, 2412000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM125', 172, '2024-10-19 05:30:02', 49, 1448000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM126', 154, '2025-04-08 05:30:02', 11, 1576000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM127', 11, '2024-08-31 05:30:02', 21, 1990000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM128', 83, '2024-11-09 05:30:02', 10, 682000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM129', 173, '2025-04-22 05:30:02', 17, 418000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM130', 125, '2025-01-29 05:30:02', 15, 2661000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM131', 5, '2025-05-01 05:30:02', 48, 843000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM132', 91, '2025-03-05 05:30:02', 24, 633000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM133', 178, '2025-04-06 05:30:02', 30, 2894000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM134', 5, '2025-02-28 05:30:02', 16, 1436000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM135', 79, '2025-03-21 05:30:02', 37, 127000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM136', 97, '2024-09-30 05:30:02', 29, 334000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM137', 186, '2025-05-19 05:30:02', 25, 333000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM138', 11, '2024-12-12 05:30:02', 6, 2805000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM139', 92, '2024-07-28 05:30:02', 11, 2987000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM140', 144, '2024-09-30 05:30:02', 15, 894000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM141', 87, '2024-10-09 05:30:02', 44, 1863000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM142', 123, '2024-12-31 05:30:02', 21, 356000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM143', 110, '2025-04-11 05:30:02', 27, 2927000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM144', 33, '2024-08-13 05:30:02', 9, 2670000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM145', 20, '2024-07-17 05:30:02', 26, 229000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM146', 103, '2025-05-18 05:30:02', 27, 2771000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM147', 197, '2025-05-23 05:30:02', 32, 2742000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM148', 9, '2024-11-21 05:30:02', 24, 1003000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM149', 94, '2025-05-21 05:30:02', 9, 729000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM150', 89, '2025-05-20 05:30:02', 26, 1542000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM151', 67, '2024-07-10 05:30:02', 30, 2908000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM152', 140, '2025-03-19 05:30:02', 14, 837000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM153', 180, '2025-02-20 05:30:02', 41, 2450000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM154', 195, '2024-06-19 05:30:02', 27, 2731000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM155', 10, '2024-09-15 05:30:02', 36, 355000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM156', 155, '2025-05-01 05:30:02', 47, 2856000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM157', 194, '2025-06-01 05:30:02', 14, 404000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM158', 107, '2024-09-06 05:30:02', 20, 371000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM159', 166, '2025-02-23 05:30:02', 33, 2574000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM160', 59, '2024-06-22 05:30:02', 17, 2534000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM161', 119, '2025-06-06 05:30:02', 36, 232000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM162', 130, '2024-07-03 05:30:02', 10, 1785000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM163', 15, '2025-05-07 05:30:02', 45, 1096000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM164', 32, '2024-12-07 05:30:02', 44, 180000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM165', 174, '2025-01-30 05:30:02', 38, 1492000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM166', 135, '2025-02-25 05:30:02', 29, 2037000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM167', 142, '2024-11-25 05:30:02', 8, 1699000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM168', 101, '2024-11-13 05:30:02', 20, 438000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM169', 166, '2025-01-05 05:30:02', 41, 2783000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM170', 110, '2024-11-21 05:30:02', 16, 2469000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM171', 152, '2024-07-01 05:30:02', 25, 1378000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM172', 5, '2024-11-27 05:30:02', 15, 1562000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM173', 80, '2024-09-03 05:30:02', 13, 969000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM174', 140, '2025-05-30 05:30:02', 29, 1237000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM175', 49, '2024-10-15 05:30:02', 9, 681000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM176', 193, '2025-04-18 05:30:02', 38, 2838000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM177', 121, '2024-09-10 05:30:02', 40, 267000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM178', 131, '2025-02-12 05:30:02', 42, 2550000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM179', 28, '2024-07-10 05:30:02', 21, 1403000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM180', 21, '2024-06-26 05:30:02', 32, 2585000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM181', 71, '2025-06-01 05:30:02', 32, 584000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM182', 28, '2024-11-15 05:30:02', 12, 1786000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM183', 82, '2025-02-27 05:30:02', 37, 915000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM184', 153, '2025-06-05 05:30:02', 11, 145000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM185', 115, '2024-11-03 05:30:02', 16, 2812000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM186', 178, '2025-02-18 05:30:02', 27, 2090000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM187', 161, '2025-01-05 05:30:02', 43, 681000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM188', 189, '2024-12-21 05:30:02', 42, 1151000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM189', 97, '2025-06-05 05:30:02', 7, 2772000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM190', 32, '2024-07-21 05:30:02', 50, 2481000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM191', 171, '2024-12-12 05:30:02', 18, 728000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM192', 169, '2025-05-01 05:30:02', 29, 1928000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM193', 78, '2024-10-03 05:30:02', 35, 958000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM194', 192, '2025-05-30 05:30:02', 36, 533000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM195', 19, '2024-09-09 05:30:02', 31, 2483000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM196', 155, '2024-06-12 05:30:02', 42, 371000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM197', 198, '2024-10-12 05:30:02', 50, 1251000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM198', 26, '2025-04-29 05:30:02', 18, 146000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM199', 144, '2025-05-05 05:30:02', 17, 2927000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM200', 121, '2025-03-25 05:30:02', 26, 1597000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM201', 82, '2025-02-09 05:30:02', 34, 2543000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM202', 122, '2024-12-16 05:30:02', 45, 2451000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM203', 196, '2025-03-09 05:30:02', 42, 704000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM204', 98, '2025-06-05 05:30:02', 7, 2977000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM205', 191, '2025-04-06 05:30:02', 6, 728000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM206', 118, '2025-03-10 05:30:02', 46, 2279000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM207', 3, '2025-02-04 05:30:02', 19, 484000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM208', 120, '2025-05-29 05:30:02', 24, 2326000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM209', 165, '2024-07-24 05:30:02', 6, 1718000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM210', 149, '2024-07-15 05:30:02', 37, 1050000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM211', 41, '2025-04-16 05:30:02', 47, 1866000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM212', 25, '2024-08-02 05:30:02', 20, 365000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM213', 14, '2024-12-28 05:30:02', 32, 2290000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM214', 148, '2025-02-06 05:30:02', 11, 2586000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM215', 167, '2025-03-19 05:30:02', 48, 1050000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM216', 116, '2024-11-06 05:30:02', 11, 1759000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM217', 3, '2024-11-21 05:30:02', 49, 1467000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM218', 160, '2025-01-23 05:30:02', 15, 1712000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM219', 83, '2024-12-28 05:30:02', 24, 655000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM220', 199, '2025-03-23 05:30:02', 34, 2612000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM221', 42, '2024-06-22 05:30:02', 24, 746000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM222', 187, '2024-11-01 05:30:02', 9, 2122000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM223', 28, '2025-02-18 05:30:02', 5, 1944000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM224', 166, '2024-08-08 05:30:02', 28, 410000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM225', 200, '2024-11-27 05:30:02', 37, 1466000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM226', 16, '2025-03-23 05:30:02', 28, 2416000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM227', 61, '2024-07-03 05:30:02', 39, 1817000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM228', 150, '2024-09-24 05:30:02', 30, 418000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM229', 19, '2025-05-12 05:30:02', 35, 1156000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM230', 74, '2025-05-15 05:30:02', 39, 2567000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM231', 18, '2024-08-12 05:30:02', 22, 828000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM232', 86, '2025-03-10 05:30:02', 8, 1089000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM233', 176, '2025-03-22 05:30:02', 6, 1707000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM234', 162, '2024-12-29 05:30:02', 32, 898000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM235', 180, '2025-04-08 05:30:02', 44, 1316000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM236', 166, '2025-05-14 05:30:02', 46, 536000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM237', 93, '2025-02-08 05:30:02', 16, 1358000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM238', 62, '2024-10-11 05:30:02', 43, 2274000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM239', 129, '2024-07-26 05:30:02', 23, 1949000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM240', 133, '2025-02-25 05:30:02', 26, 1971000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM241', 127, '2024-12-23 05:30:02', 24, 1458000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM242', 188, '2025-05-25 05:30:02', 35, 1792000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM243', 6, '2024-08-06 05:30:02', 27, 534000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM244', 144, '2024-12-15 05:30:02', 44, 501000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM245', 30, '2024-07-13 05:30:02', 29, 2025000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM246', 154, '2025-05-07 05:30:02', 48, 2087000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM247', 49, '2025-03-03 05:30:02', 10, 2196000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM248', 68, '2025-04-05 05:30:02', 48, 2379000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM249', 74, '2024-08-08 05:30:02', 24, 1677000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM250', 54, '2025-03-29 05:30:02', 32, 2574000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM251', 160, '2024-10-25 05:30:02', 6, 2940000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM252', 97, '2024-08-16 05:30:02', 41, 338000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM253', 86, '2025-02-08 05:30:02', 16, 2908000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM254', 110, '2024-06-15 05:30:02', 11, 196000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM255', 18, '2024-06-08 05:30:02', 12, 417000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM256', 63, '2024-12-06 05:30:02', 14, 2653000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM257', 128, '2025-01-27 05:30:02', 10, 615000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM258', 147, '2025-05-28 05:30:02', 39, 1835000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM259', 167, '2024-11-16 05:30:02', 5, 1428000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM260', 60, '2025-04-10 05:30:02', 43, 2939000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM261', 1, '2024-12-06 05:30:02', 40, 1765000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM262', 18, '2024-07-09 05:30:02', 31, 2464000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM263', 76, '2025-05-07 05:30:02', 32, 2532000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM264', 132, '2024-08-22 05:30:02', 48, 1397000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM265', 93, '2025-06-01 05:30:02', 23, 890000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM266', 105, '2025-01-11 05:30:02', 45, 747000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM267', 117, '2025-03-09 05:30:02', 29, 1444000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM268', 144, '2024-07-30 05:30:02', 41, 1182000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM269', 35, '2025-02-16 05:30:02', 18, 329000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM270', 192, '2024-09-25 05:30:02', 17, 272000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM271', 48, '2024-08-17 05:30:02', 31, 2579000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM272', 13, '2025-05-14 05:30:02', 34, 285000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM273', 178, '2024-10-24 05:30:02', 27, 2277000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM274', 31, '2025-04-02 05:30:02', 46, 1479000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM275', 138, '2024-11-27 05:30:02', 28, 957000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM276', 87, '2024-08-07 05:30:02', 36, 806000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM277', 110, '2025-06-03 05:30:02', 25, 2637000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM278', 148, '2024-06-11 05:30:02', 34, 584000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM279', 172, '2024-08-04 05:30:02', 47, 2205000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM280', 88, '2025-01-21 05:30:02', 49, 698000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM281', 167, '2024-09-27 05:30:02', 29, 390000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM282', 186, '2025-03-08 05:30:02', 21, 989000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM283', 67, '2024-10-21 05:30:02', 29, 1781000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM284', 42, '2024-09-05 05:30:02', 32, 2666000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM285', 145, '2024-08-04 05:30:02', 22, 409000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM286', 90, '2025-01-27 05:30:02', 30, 632000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM287', 51, '2025-04-03 05:30:02', 11, 2665000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM288', 37, '2024-11-19 05:30:02', 47, 737000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM289', 62, '2024-07-22 05:30:02', 43, 2331000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM290', 126, '2024-09-03 05:30:02', 12, 2447000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM291', 43, '2025-01-31 05:30:02', 6, 224000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM292', 53, '2025-03-18 05:30:02', 8, 589000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM293', 187, '2024-06-30 05:30:02', 19, 891000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM294', 198, '2025-05-21 05:30:02', 27, 2679000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM295', 144, '2025-05-01 05:30:02', 31, 2078000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM296', 81, '2025-01-13 05:30:02', 39, 439000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM297', 101, '2024-09-12 05:30:02', 30, 2501000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM298', 78, '2024-08-24 05:30:02', 39, 709000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM299', 55, '2025-03-08 05:30:02', 7, 346000);
INSERT INTO Imports (ImportCode, CategoryID, CreatedAt, Quantity, Price) VALUES ('IM300', 9, '2024-07-24 05:30:02', 19, 608000);

-- Insert data into Products
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (144, 'PRD0100', 1, 268);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (142, 'PRD0101', 1, 167);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (21, 'PRD0102', 2, 180);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (105, 'PRD0103', 0, 266);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (129, 'PRD0104', 2, 239);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (152, 'PRD0105', 0, 29);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (92, 'PRD0106', 1, 139);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (181, 'PRD0107', 2, 91);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (140, 'PRD0108', 0, 100);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (197, 'PRD0109', 0, 147);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (153, 'PRD0110', 2, 184);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (29, 'PRD0111', 0, 22);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (6, 'PRD0112', 0, 243);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (62, 'PRD0113', 2, 289);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (199, 'PRD0114', 1, 220);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (152, 'PRD0115', 2, 29);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (50, 'PRD0116', 1, 115);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (176, 'PRD0117', 1, 233);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (35, 'PRD0118', 2, 269);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (195, 'PRD0119', 1, 154);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (135, 'PRD0120', 0, 21);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (192, 'PRD0121', 1, 194);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (173, 'PRD0122', 1, 70);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (144, 'PRD0123', 0, 268);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (5, 'PRD0124', 1, 172);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (107, 'PRD0125', 1, 158);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (164, 'PRD0126', 0, 30);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (57, 'PRD0127', 1, 62);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (107, 'PRD0128', 0, 158);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (37, 'PRD0129', 0, 123);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (118, 'PRD0130', 1, 206);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (130, 'PRD0131', 0, 162);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (181, 'PRD0132', 2, 102);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (147, 'PRD0133', 1, 258);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (175, 'PRD0134', 0, 103);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (197, 'PRD0135', 2, 110);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (15, 'PRD0136', 1, 163);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (163, 'PRD0137', 0, 81);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (125, 'PRD0138', 0, 130);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (25, 'PRD0139', 1, 212);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (76, 'PRD0140', 1, 4);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (123, 'PRD0141', 2, 6);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (60, 'PRD0142', 0, 260);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (69, 'PRD0143', 1, 20);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (3, 'PRD0144', 0, 217);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (19, 'PRD0145', 1, 195);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (110, 'PRD0146', 1, 277);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (18, 'PRD0147', 1, 255);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (88, 'PRD0148', 2, 26);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (164, 'PRD0149', 2, 30);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (31, 'PRD0150', 1, 56);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (43, 'PRD0151', 2, 291);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (85, 'PRD0152', 2, 94);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (150, 'PRD0153', 0, 43);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (150, 'PRD0154', 0, 43);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (19, 'PRD0155', 1, 229);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (154, 'PRD0156', 0, 126);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (163, 'PRD0157', 1, 81);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (1, 'PRD0158', 2, 60);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (163, 'PRD0159', 0, 81);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (149, 'PRD0160', 1, 44);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (120, 'PRD0161', 0, 75);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (165, 'PRD0162', 1, 109);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (149, 'PRD0163', 1, 44);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (162, 'PRD0164', 1, 234);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (153, 'PRD0165', 1, 184);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (96, 'PRD0166', 1, 98);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (80, 'PRD0167', 0, 173);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (93, 'PRD0168', 1, 46);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (43, 'PRD0169', 0, 291);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (68, 'PRD0170', 1, 248);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (127, 'PRD0171', 0, 241);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (149, 'PRD0172', 0, 44);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (105, 'PRD0173', 2, 266);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (20, 'PRD0174', 1, 145);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (13, 'PRD0175', 0, 272);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (120, 'PRD0176', 1, 208);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (169, 'PRD0177', 1, 97);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (80, 'PRD0178', 2, 173);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (119, 'PRD0179', 2, 54);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (175, 'PRD0180', 2, 103);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (107, 'PRD0181', 1, 49);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (110, 'PRD0182', 1, 104);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (131, 'PRD0183', 0, 86);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (175, 'PRD0184', 2, 103);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (95, 'PRD0185', 2, 25);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (164, 'PRD0186', 1, 10);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (173, 'PRD0187', 1, 70);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (91, 'PRD0188', 2, 132);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (47, 'PRD0189', 1, 107);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (140, 'PRD0190', 2, 174);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (152, 'PRD0191', 1, 29);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (133, 'PRD0192', 0, 83);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (51, 'PRD0193', 0, 287);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (200, 'PRD0194', 1, 225);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (150, 'PRD0195', 1, 43);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (147, 'PRD0196', 1, 258);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (188, 'PRD0197', 1, 242);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (177, 'PRD0198', 0, 31);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (178, 'PRD0199', 0, 273);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (118, 'PRD0200', 2, 206);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (29, 'PRD0201', 2, 22);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (162, 'PRD0202', 1, 234);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (65, 'PRD0203', 0, 55);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (127, 'PRD0204', 0, 2);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (178, 'PRD0205', 0, 73);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (164, 'PRD0206', 1, 30);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (178, 'PRD0207', 0, 113);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (19, 'PRD0208', 2, 195);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (196, 'PRD0209', 1, 203);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (103, 'PRD0210', 1, 5);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (125, 'PRD0211', 1, 130);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (193, 'PRD0212', 1, 176);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (161, 'PRD0213', 0, 187);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (172, 'PRD0214', 1, 279);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (127, 'PRD0215', 1, 2);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (9, 'PRD0216', 2, 300);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (29, 'PRD0217', 0, 22);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (152, 'PRD0218', 1, 29);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (140, 'PRD0219', 2, 152);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (43, 'PRD0220', 0, 291);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (132, 'PRD0221', 0, 45);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (191, 'PRD0222', 2, 205);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (180, 'PRD0223', 2, 235);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (89, 'PRD0224', 1, 150);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (138, 'PRD0225', 2, 57);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (74, 'PRD0226', 2, 118);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (169, 'PRD0227', 1, 50);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (191, 'PRD0228', 1, 205);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (74, 'PRD0229', 2, 249);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (101, 'PRD0230', 0, 297);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (181, 'PRD0231', 2, 102);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (110, 'PRD0232', 2, 143);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (88, 'PRD0233', 1, 280);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (147, 'PRD0234', 2, 258);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (59, 'PRD0235', 2, 160);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (10, 'PRD0236', 0, 23);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (177, 'PRD0237', 1, 31);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (56, 'PRD0238', 2, 121);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (51, 'PRD0239', 0, 287);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (122, 'PRD0240', 1, 202);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (63, 'PRD0241', 2, 256);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (86, 'PRD0242', 1, 232);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (154, 'PRD0243', 1, 126);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (12, 'PRD0244', 2, 32);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (191, 'PRD0245', 0, 205);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (191, 'PRD0246', 1, 205);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (117, 'PRD0247', 0, 267);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (126, 'PRD0248', 1, 290);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (165, 'PRD0249', 0, 209);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (55, 'PRD0250', 1, 299);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (62, 'PRD0251', 0, 238);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (187, 'PRD0252', 1, 222);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (123, 'PRD0253', 2, 6);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (76, 'PRD0254', 0, 263);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (89, 'PRD0255', 2, 150);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (3, 'PRD0256', 2, 38);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (194, 'PRD0257', 2, 157);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (21, 'PRD0258', 0, 180);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (47, 'PRD0259', 0, 42);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (89, 'PRD0260', 2, 150);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (70, 'PRD0261', 1, 69);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (169, 'PRD0262', 1, 50);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (117, 'PRD0263', 0, 267);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (15, 'PRD0264', 2, 163);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (117, 'PRD0265', 0, 267);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (56, 'PRD0266', 2, 121);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (144, 'PRD0267', 1, 199);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (171, 'PRD0268', 0, 191);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (83, 'PRD0269', 2, 7);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (187, 'PRD0270', 1, 222);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (60, 'PRD0271', 1, 33);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (19, 'PRD0272', 2, 195);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (92, 'PRD0273', 0, 139);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (9, 'PRD0274', 2, 148);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (1, 'PRD0275', 2, 261);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (68, 'PRD0276', 1, 248);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (165, 'PRD0277', 1, 209);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (122, 'PRD0278', 2, 202);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (130, 'PRD0279', 2, 162);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (53, 'PRD0280', 0, 292);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (192, 'PRD0281', 0, 270);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (6, 'PRD0282', 1, 14);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (175, 'PRD0283', 0, 103);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (9, 'PRD0284', 0, 300);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (11, 'PRD0285', 0, 127);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (181, 'PRD0286', 2, 102);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (163, 'PRD0287', 1, 81);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (24, 'PRD0288', 2, 64);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (95, 'PRD0289', 1, 25);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (81, 'PRD0290', 2, 108);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (57, 'PRD0291', 2, 62);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (68, 'PRD0292', 2, 248);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (120, 'PRD0293', 0, 75);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (68, 'PRD0294', 1, 248);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (9, 'PRD0295', 0, 148);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (145, 'PRD0296', 2, 285);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (38, 'PRD0297', 1, 16);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (51, 'PRD0298', 2, 48);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (82, 'PRD0299', 0, 34);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (53, 'PRD0300', 2, 292);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (57, 'PRD0301', 0, 62);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (28, 'PRD0302', 0, 223);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (117, 'PRD0303', 0, 267);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (57, 'PRD0304', 2, 62);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (42, 'PRD0305', 0, 284);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (162, 'PRD0306', 1, 234);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (187, 'PRD0307', 2, 293);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (35, 'PRD0308', 1, 269);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (74, 'PRD0309', 2, 249);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (93, 'PRD0310', 1, 46);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (1, 'PRD0311', 1, 261);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (15, 'PRD0312', 1, 163);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (122, 'PRD0313', 0, 202);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (60, 'PRD0314', 1, 33);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (192, 'PRD0315', 0, 194);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (158, 'PRD0316', 1, 61);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (59, 'PRD0317', 1, 160);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (126, 'PRD0318', 2, 290);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (29, 'PRD0319', 1, 22);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (155, 'PRD0320', 1, 156);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (176, 'PRD0321', 0, 233);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (59, 'PRD0322', 1, 99);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (197, 'PRD0323', 0, 110);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (192, 'PRD0324', 1, 194);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (77, 'PRD0325', 2, 37);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (56, 'PRD0326', 0, 63);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (29, 'PRD0327', 1, 22);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (83, 'PRD0328', 1, 7);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (42, 'PRD0329', 0, 284);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (181, 'PRD0330', 1, 102);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (55, 'PRD0331', 1, 299);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (116, 'PRD0332', 2, 216);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (3, 'PRD0333', 1, 38);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (26, 'PRD0334', 2, 198);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (2, 'PRD0335', 1, 96);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (74, 'PRD0336', 0, 230);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (84, 'PRD0337', 0, 11);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (63, 'PRD0338', 1, 256);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (12, 'PRD0339', 2, 32);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (13, 'PRD0340', 2, 272);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (28, 'PRD0341', 0, 179);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (153, 'PRD0342', 0, 184);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (160, 'PRD0343', 1, 251);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (165, 'PRD0344', 2, 209);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (171, 'PRD0345', 0, 191);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (47, 'PRD0346', 2, 107);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (94, 'PRD0347', 1, 149);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (82, 'PRD0348', 0, 201);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (48, 'PRD0349', 0, 271);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (91, 'PRD0350', 0, 132);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (119, 'PRD0351', 1, 161);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (16, 'PRD0352', 0, 226);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (105, 'PRD0353', 0, 266);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (93, 'PRD0354', 2, 265);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (160, 'PRD0355', 1, 218);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (127, 'PRD0356', 2, 241);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (148, 'PRD0357', 2, 214);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (84, 'PRD0358', 2, 11);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (26, 'PRD0359', 0, 198);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (84, 'PRD0360', 2, 11);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (98, 'PRD0361', 0, 204);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (57, 'PRD0362', 2, 62);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (20, 'PRD0363', 1, 145);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (87, 'PRD0364', 1, 141);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (47, 'PRD0365', 2, 107);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (63, 'PRD0366', 1, 256);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (14, 'PRD0367', 2, 80);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (86, 'PRD0368', 0, 232);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (138, 'PRD0369', 2, 275);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (16, 'PRD0370', 0, 226);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (86, 'PRD0371', 0, 253);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (19, 'PRD0372', 0, 229);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (192, 'PRD0373', 2, 270);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (48, 'PRD0374', 2, 271);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (56, 'PRD0375', 0, 112);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (148, 'PRD0376', 1, 278);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (74, 'PRD0377', 2, 118);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (121, 'PRD0378', 0, 200);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (5, 'PRD0379', 2, 134);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (19, 'PRD0380', 1, 229);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (24, 'PRD0381', 2, 12);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (10, 'PRD0382', 2, 155);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (63, 'PRD0383', 2, 256);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (110, 'PRD0384', 2, 143);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (68, 'PRD0385', 0, 248);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (42, 'PRD0386', 0, 221);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (2, 'PRD0387', 1, 96);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (174, 'PRD0388', 1, 165);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (126, 'PRD0389', 1, 290);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (160, 'PRD0390', 2, 251);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (134, 'PRD0391', 0, 41);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (65, 'PRD0392', 0, 55);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (126, 'PRD0393', 1, 290);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (102, 'PRD0394', 1, 84);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (24, 'PRD0395', 2, 85);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (186, 'PRD0396', 1, 282);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (37, 'PRD0397', 2, 3);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (16, 'PRD0398', 2, 226);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (5, 'PRD0399', 0, 172);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (118, 'PRD0400', 2, 206);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (167, 'PRD0401', 2, 215);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (16, 'PRD0402', 0, 226);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (173, 'PRD0403', 2, 129);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (69, 'PRD0404', 0, 92);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (193, 'PRD0405', 1, 176);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (71, 'PRD0406', 2, 181);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (50, 'PRD0407', 2, 115);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (48, 'PRD0408', 0, 271);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (32, 'PRD0409', 2, 1);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (50, 'PRD0410', 2, 115);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (193, 'PRD0411', 1, 176);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (68, 'PRD0412', 0, 248);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (68, 'PRD0413', 1, 248);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (175, 'PRD0414', 2, 103);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (94, 'PRD0415', 0, 149);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (15, 'PRD0416', 1, 163);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (142, 'PRD0417', 0, 167);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (164, 'PRD0418', 1, 10);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (192, 'PRD0419', 2, 270);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (60, 'PRD0420', 1, 18);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (191, 'PRD0421', 2, 205);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (87, 'PRD0422', 0, 141);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (140, 'PRD0423', 2, 100);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (42, 'PRD0424', 2, 284);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (20, 'PRD0425', 0, 145);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (192, 'PRD0426', 2, 194);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (142, 'PRD0427', 0, 167);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (197, 'PRD0428', 0, 147);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (138, 'PRD0429', 1, 57);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (107, 'PRD0430', 0, 158);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (138, 'PRD0431', 0, 275);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (105, 'PRD0432', 1, 266);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (24, 'PRD0433', 1, 12);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (62, 'PRD0434', 0, 289);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (116, 'PRD0435', 1, 216);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (167, 'PRD0436', 0, 215);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (166, 'PRD0437', 2, 224);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (95, 'PRD0438', 0, 25);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (163, 'PRD0439', 0, 81);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (158, 'PRD0440', 1, 61);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (26, 'PRD0441', 2, 198);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (77, 'PRD0442', 0, 37);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (81, 'PRD0443', 2, 108);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (161, 'PRD0444', 0, 187);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (125, 'PRD0445', 0, 130);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (174, 'PRD0446', 2, 165);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (21, 'PRD0447', 1, 180);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (84, 'PRD0448', 2, 11);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (181, 'PRD0449', 1, 91);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (74, 'PRD0450', 1, 249);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (87, 'PRD0451', 1, 276);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (2, 'PRD0452', 2, 96);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (122, 'PRD0453', 1, 202);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (149, 'PRD0454', 1, 210);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (191, 'PRD0455', 1, 205);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (72, 'PRD0456', 1, 71);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (49, 'PRD0457', 0, 87);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (132, 'PRD0458', 0, 95);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (110, 'PRD0459', 2, 143);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (86, 'PRD0460', 1, 232);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (33, 'PRD0461', 2, 144);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (96, 'PRD0462', 1, 98);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (38, 'PRD0463', 1, 16);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (13, 'PRD0464', 2, 272);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (173, 'PRD0465', 1, 72);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (49, 'PRD0466', 2, 58);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (87, 'PRD0467', 0, 141);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (144, 'PRD0468', 1, 244);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (55, 'PRD0469', 2, 299);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (194, 'PRD0470', 2, 157);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (140, 'PRD0471', 0, 100);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (59, 'PRD0472', 2, 160);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (54, 'PRD0473', 0, 250);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (96, 'PRD0474', 1, 98);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (41, 'PRD0475', 2, 211);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (54, 'PRD0476', 0, 250);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (43, 'PRD0477', 2, 291);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (129, 'PRD0478', 1, 239);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (123, 'PRD0479', 2, 6);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (196, 'PRD0480', 0, 203);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (115, 'PRD0481', 0, 51);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (42, 'PRD0482', 2, 284);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (11, 'PRD0483', 0, 127);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (177, 'PRD0484', 2, 31);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (130, 'PRD0485', 1, 162);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (33, 'PRD0486', 0, 144);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (121, 'PRD0487', 1, 177);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (131, 'PRD0488', 1, 178);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (76, 'PRD0489', 2, 28);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (177, 'PRD0490', 0, 31);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (199, 'PRD0491', 0, 220);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (149, 'PRD0492', 0, 44);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (72, 'PRD0493', 2, 71);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (131, 'PRD0494', 2, 178);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (62, 'PRD0495', 0, 238);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (82, 'PRD0496', 0, 90);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (138, 'PRD0497', 2, 57);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (2, 'PRD0498', 2, 96);
INSERT INTO Products (CategoryID, ProductCode, Status, ImportID) VALUES (158, 'PRD0499', 2, 61);

-- Insert data into Warranties
INSERT INTO Warranties (WarrantyPeriod, Description) VALUES (3, N'3-month limited warranty covering manufacturer defects.');
INSERT INTO Warranties (WarrantyPeriod, Description) VALUES (6, N'6-month standard warranty with replacement options.');
INSERT INTO Warranties (WarrantyPeriod, Description) VALUES (12, N'12-month full warranty including parts and labor.');
INSERT INTO Warranties (WarrantyPeriod, Description) VALUES (24, N'24-month extended warranty with priority support.');

INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 1, 156820, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 1, 89027, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 1, 97200, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 2, 115342, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 2, 374962, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 2, 53829, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 3, 496339, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 4, 165743, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 4, 155628, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 4, 450361, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 5, 255019, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 6, 374823, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 6, 428570, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 6, 147971, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 7, 479335, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 7, 289827, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 7, 334421, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 8, 339192, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 9, 338276, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 10, 394175, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 11, 287302, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 12, 425416, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 12, 249685, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 12, 383527, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 13, 103964, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 13, 427141, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 14, 168675, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 14, 112847, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 14, 192019, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 15, 488503, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 15, 479436, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 15, 229079, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 16, 364201, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 17, 202257, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 17, 476210, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 17, 415586, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 18, 389279, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 18, 440428, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 18, 107839, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 19, 222213, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 19, 453656, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 20, 299620, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 20, 369818, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 20, 409664, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 21, 489974, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 21, 91753, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (1, 22, 353213, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 23, 372523, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 23, 233318, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 24, 377094, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 24, 307255, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 25, 297511, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 25, 59917, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 25, 97589, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 26, 119069, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 27, 357872, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 28, 462604, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 28, 352074, 1);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (2, 29, 115107, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (4, 29, 119675, 0);
INSERT INTO WarrantyDetails (WarrantyID, BrandComID, Price, Status) VALUES (3, 30, 303555, 1);

INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-27 05:34:02', N'Customer 2 Address #1', 3423961, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-02-14 05:34:02', N'Customer 2 Address #2', 1820810, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-03-26 05:34:02', N'Customer 2 Address #3', 1989825, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2024-12-30 05:34:02', N'Customer 2 Address #4', 9183998, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-07 05:34:02', N'Customer 2 Address #5', 5318385, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-02-14 05:34:02', N'Customer 2 Address #6', 8570968, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-26 05:34:02', N'Customer 2 Address #7', 3150723, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-20 05:34:02', N'Customer 2 Address #8', 7980484, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-22 05:34:02', N'Customer 2 Address #9', 6167904, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2024-12-22 05:34:02', N'Customer 2 Address #10', 2931928, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-03-12 05:34:02', N'Customer 2 Address #11', 5933384, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-21 05:34:02', N'Customer 2 Address #12', 3195418, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-23 05:34:02', N'Customer 2 Address #13', 7339461, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-02-25 05:34:02', N'Customer 2 Address #14', 4071959, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-25 05:34:02', N'Customer 2 Address #15', 8713457, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-14 05:34:02', N'Customer 2 Address #16', 9628131, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-14 05:34:02', N'Customer 2 Address #17', 3978813, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-03-13 05:34:02', N'Customer 2 Address #18', 9038006, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-26 05:34:02', N'Customer 2 Address #19', 8346830, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-22 05:34:02', N'Customer 2 Address #20', 5783144, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-10 05:34:02', N'Customer 2 Address #21', 3744954, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-07 05:34:02', N'Customer 2 Address #22', 4999416, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-02-02 05:34:02', N'Customer 2 Address #23', 4143215, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-02-15 05:34:02', N'Customer 2 Address #24', 1839103, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-19 05:34:02', N'Customer 2 Address #25', 8539022, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-19 05:34:02', N'Customer 2 Address #26', 8583558, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2024-12-10 05:34:02', N'Customer 2 Address #27', 1981950, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-03-21 05:34:02', N'Customer 2 Address #28', 7824440, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2024-12-16 05:34:02', N'Customer 2 Address #29', 1194083, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-02-02 05:34:02', N'Customer 2 Address #30', 6303636, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2024-12-19 05:34:02', N'Customer 2 Address #31', 3729645, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-03-09 05:34:02', N'Customer 2 Address #32', 1602751, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-05 05:34:02', N'Customer 2 Address #33', 8300441, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-18 05:34:02', N'Customer 2 Address #34', 3393511, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-10 05:34:02', N'Customer 2 Address #35', 6134775, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2024-12-29 05:34:02', N'Customer 2 Address #36', 7003875, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-08 05:34:02', N'Customer 2 Address #37', 5921344, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-15 05:34:02', N'Customer 2 Address #38', 9742491, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-30 05:34:02', N'Customer 2 Address #39', 6985144, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-03-25 05:34:02', N'Customer 2 Address #40', 5780666, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-09 05:34:02', N'Customer 2 Address #41', 3699020, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-02-23 05:34:02', N'Customer 2 Address #42', 8325961, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-13 05:34:02', N'Customer 2 Address #43', 5748833, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-02-04 05:34:02', N'Customer 2 Address #44', 5175889, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-01 05:34:02', N'Customer 2 Address #45', 4346888, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-01-21 05:34:02', N'Customer 2 Address #46', 7250223, 2);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-25 05:34:02', N'Customer 2 Address #47', 2129085, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-05-24 05:34:02', N'Customer 2 Address #48', 4943997, 0);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2025-04-27 05:34:02', N'Customer 2 Address #49', 7632869, 1);
INSERT INTO Orders (CustomerID, OrderDate, Address, TotalAmount, Status) VALUES (2, '2024-12-11 05:34:02', N'Customer 2 Address #50', 9077684, 2);

-- Insert data into CartItems
INSERT INTO CartItems (UserID, CategoryID, WarrantyDetailID, Status) VALUES (2, 167, 60, 1);
INSERT INTO CartItems (UserID, CategoryID, WarrantyDetailID, Status) VALUES (2, 12, 25, 1);
INSERT INTO CartItems (UserID, CategoryID, WarrantyDetailID, Status) VALUES (2, 196, 46, 1);
INSERT INTO CartItems (UserID, CategoryID, WarrantyDetailID, Status) VALUES (2, 71, 12, 1);
INSERT INTO CartItems (UserID, CategoryID, WarrantyDetailID, Status) VALUES (2, 112, 21, 0);

-- Insert data into OrderItems
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (47, 189, 3, 1574055);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (9, 158, 2, 2870431);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (50, 123, 5, 555857);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (37, 137, 5, 533325);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (13, 17, 1, 2312204);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (10, 102, 3, 2846069);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (2, 74, 2, 1228975);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (48, 130, 1, 2619488);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (21, 193, 1, 1030626);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (31, 180, 2, 1049969);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (40, 110, 4, 564657);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (15, 187, 2, 2140903);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (32, 197, 4, 575212);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (21, 62, 2, 1109616);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (26, 113, 5, 2939906);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (13, 24, 3, 2083571);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (41, 60, 4, 829707);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (25, 73, 5, 1681415);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (11, 51, 5, 818324);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (15, 64, 5, 2741220);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (23, 121, 4, 2918323);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (45, 150, 5, 799881);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (38, 139, 1, 2074101);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (35, 129, 1, 802071);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (32, 41, 5, 1996763);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (35, 53, 2, 1320694);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (1, 62, 5, 2078736);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (22, 72, 5, 1825369);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (22, 22, 3, 897541);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (24, 186, 4, 1858335);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (18, 148, 3, 2861604);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (24, 58, 3, 1526724);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (10, 198, 5, 2465024);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (4, 46, 1, 2604873);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (3, 57, 2, 2494581);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (40, 19, 2, 858764);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (36, 189, 1, 2686278);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (37, 149, 3, 1571682);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (8, 8, 1, 2578821);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (40, 44, 3, 1103712);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (44, 88, 5, 2881894);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (1, 106, 4, 1353793);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (21, 169, 4, 2234108);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (27, 132, 1, 818047);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (11, 24, 1, 2034209);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (31, 183, 2, 2194467);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (42, 50, 2, 1196308);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (41, 129, 4, 1542231);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (45, 43, 4, 2702220);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (36, 83, 2, 988291);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (15, 130, 3, 2045875);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (33, 193, 2, 1466773);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (31, 185, 2, 2080050);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (43, 63, 2, 1819381);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (32, 108, 1, 1725746);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (9, 128, 3, 1647938);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (30, 9, 3, 1094273);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (2, 123, 2, 1938322);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (3, 24, 1, 736842);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (1, 122, 3, 1036920);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (29, 181, 1, 548280);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (32, 143, 4, 2438760);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (1, 157, 4, 809488);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (31, 23, 5, 2525542);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (21, 55, 5, 1506734);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (44, 86, 1, 2110930);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (45, 116, 2, 1591762);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (33, 25, 1, 589818);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (41, 25, 2, 2705563);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (15, 16, 3, 2163898);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (22, 36, 4, 858854);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (11, 115, 5, 1925223);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (45, 144, 1, 845778);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (42, 33, 3, 2741327);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (4, 107, 5, 2334405);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (32, 198, 3, 1466059);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (26, 157, 5, 740362);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (37, 125, 3, 950939);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (17, 106, 2, 1431877);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (16, 188, 3, 2754522);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (19, 164, 3, 1715993);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (32, 56, 5, 2263097);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (20, 91, 4, 2866788);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (36, 136, 2, 1726843);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (11, 54, 2, 1552664);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (9, 14, 4, 2276734);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (35, 11, 4, 2641153);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (31, 140, 2, 699545);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (43, 110, 1, 1444634);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (23, 65, 4, 811226);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (28, 93, 3, 747133);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (26, 165, 3, 1334319);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (35, 143, 2, 1011300);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (26, 66, 1, 1648632);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (44, 68, 4, 2756880);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (17, 141, 2, 1369905);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (29, 133, 4, 1142642);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (36, 169, 5, 2567111);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (35, 64, 5, 2566180);
INSERT INTO OrderItems (OrderID, CategoryID, Quantity, Price) VALUES (6, 168, 5, 1456049);

-- Insert valid OrderDetails data
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (89, 8, 56, 7388968, 160277, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (37, 339, 20, 960190, 368646, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (79, 271, 4, 6064089, 152252, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (52, 41, 56, 4274287, 171350, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (5, 294, 35, 1768543, 131820, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (15, 134, 16, 2703833, 150941, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (71, 57, 29, 5893887, 267216, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (57, 120, 8, 863208, 50716, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (87, 326, 12, 5176824, 329730, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (21, 363, 7, 6234664, 266637, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (69, 263, 16, 1477091, 354040, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (54, 146, 49, 5565144, 282424, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (34, 378, 41, 1807914, 253927, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (27, 337, 12, 7407705, 255069, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (7, 200, 48, 7489422, 301157, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (72, 189, 54, 6181360, 231211, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (6, 311, 24, 1857189, 235284, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (39, 205, 14, 7783572, 234365, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (14, 204, 54, 3850997, 187873, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (72, 278, 36, 6182091, 131803, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (24, 68, 3, 7168274, 138444, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (96, 396, 23, 2957904, 182152, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (49, 150, 18, 3709789, 83156, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (78, 105, 16, 6871942, 300270, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (41, 358, 56, 6525738, 107507, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (80, 354, 11, 6421907, 107964, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (90, 369, 51, 7267824, 245838, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (52, 159, 51, 6099099, 323164, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (15, 226, 44, 6310473, 318041, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (96, 189, 15, 4496562, 196787, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (4, 177, 13, 693247, 381321, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (35, 356, 41, 3495170, 282157, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (31, 155, 1, 5804911, 94303, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (1, 387, 13, 4856048, 369186, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (17, 21, 6, 753417, 237273, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (84, 193, 25, 1417232, 97496, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (96, 382, 15, 1905121, 152792, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (81, 342, 15, 3186317, 110707, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (84, 43, 3, 7380214, 121118, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (65, 210, 35, 2948116, 301470, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (65, 102, 30, 5743629, 356169, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (51, 391, 15, 2953038, 223403, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (60, 52, 37, 5329923, 172106, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (91, 64, 22, 2882146, 131203, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (96, 116, 8, 5971142, 316561, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (88, 371, 40, 2463944, 274986, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (77, 282, 3, 6858422, 268308, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (42, 22, 46, 7678353, 268726, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (97, 173, 9, 4805004, 215133, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (96, 161, 5, 6120755, 314906, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (29, 102, 33, 1765395, 144371, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (31, 293, 18, 7052910, 275568, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (68, 328, 6, 1814431, 287288, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (74, 230, 6, 6878197, 306349, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (94, 269, 1, 6409853, 291953, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (65, 191, 18, 3425009, 384759, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (11, 113, 44, 4698756, 105628, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (17, 10, 50, 4118010, 214080, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (100, 219, 59, 4943555, 147083, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (47, 211, 24, 3607982, 298651, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (32, 220, 1, 5903862, 289952, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (80, 166, 34, 5109259, 197465, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (8, 73, 57, 6054858, 81750, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (57, 258, 22, 2227045, 182803, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (41, 349, 6, 5157715, 194639, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (47, 46, 29, 3140758, 132578, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (83, 144, 48, 6157362, 173062, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (30, 87, 13, 6700594, 373693, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (63, 199, 50, 740233, 110588, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (2, 124, 12, 7480906, 358454, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (72, 136, 42, 2552419, 138009, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (72, 231, 5, 1876933, 129750, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (31, 212, 36, 648127, 148696, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (15, 328, 60, 7280598, 290129, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (15, 207, 30, 6411560, 135282, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (63, 214, 18, 6639401, 373301, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (21, 337, 49, 4145470, 130013, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (90, 349, 7, 5508606, 164324, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (26, 120, 24, 3694692, 56401, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (68, 16, 57, 2155155, 171912, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (25, 67, 59, 2722824, 232302, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (82, 41, 22, 4283848, 94087, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (11, 49, 16, 6467277, 232365, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (51, 140, 6, 3599383, 377856, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (34, 266, 50, 3000197, 311831, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (85, 106, 10, 3579084, 158107, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (84, 279, 54, 4380833, 146814, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (33, 337, 39, 5590586, 122164, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (58, 72, 61, 5392698, 302265, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (89, 82, 50, 6026032, 348763, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (35, 229, 57, 6726484, 105049, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (15, 199, 20, 6910263, 216945, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (36, 292, 54, 4810067, 325435, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (30, 142, 26, 2347301, 180217, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (27, 76, 44, 5625556, 181862, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (46, 35, 7, 2384575, 79088, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (31, 352, 25, 4009891, 215482, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (5, 273, 12, 5524722, 267496, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (60, 176, 54, 1361799, 399999, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (56, 154, 43, 5105364, 240349, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (93, 6, 38, 5675747, 363460, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (55, 300, 18, 559719, 322561, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (14, 53, 35, 7020211, 201260, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (63, 215, 22, 1078165, 322409, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (80, 69, 52, 3162919, 146255, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (6, 246, 2, 958618, 50151, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (21, 25, 43, 7197247, 80683, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (74, 49, 44, 4100117, 51785, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (19, 342, 48, 5029634, 199820, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (42, 275, 18, 4556652, 145740, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (48, 208, 33, 899071, 260276, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (85, 56, 36, 6792539, 169719, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (54, 294, 24, 6933481, 140346, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (15, 30, 61, 688062, 109987, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (55, 217, 16, 6446298, 240483, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (60, 300, 8, 2828479, 264633, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (35, 2, 42, 4665833, 267253, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (25, 146, 25, 7765188, 58918, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (20, 346, 33, 2011783, 345327, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (98, 157, 21, 881070, 86957, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (40, 51, 36, 2154718, 239500, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (60, 316, 33, 7816269, 341377, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (16, 331, 48, 3207907, 85891, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (88, 140, 53, 7119173, 141869, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (46, 206, 13, 914890, 120133, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (39, 357, 25, 3041787, 321571, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (47, 243, 21, 792273, 284067, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (3, 345, 37, 6642909, 98188, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (36, 183, 20, 7710055, 125391, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (3, 201, 31, 4480521, 106645, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (93, 212, 34, 2582328, 351425, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (22, 137, 4, 6823871, 153431, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (58, 282, 19, 6010275, 186147, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (42, 298, 41, 722323, 165443, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (92, 192, 23, 6664895, 369567, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (54, 265, 55, 7236820, 327325, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (92, 310, 24, 2303652, 209371, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (28, 311, 52, 1361404, 195005, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (54, 150, 40, 6127885, 57308, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (5, 300, 38, 3447586, 126926, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (43, 205, 48, 3607111, 304426, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (71, 99, 29, 1638646, 252748, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (71, 218, 47, 5807013, 257217, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (83, 88, 3, 5342062, 294316, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (74, 53, 6, 1205931, 70667, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (12, 280, 11, 4504406, 87829, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (4, 314, 59, 3942953, 188017, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (83, 119, 46, 1075877, 176772, 1);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (93, 178, 12, 6491378, 280025, 0);
INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) VALUES (8, 302, 2, 557588, 343415, 0);

INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #1 from user 2 on order item 91.', 91, '2025-06-03 05:46:35', 2, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #2 from user 2 on order item 95.', 95, '2025-03-11 05:46:35', 4, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #3 from user 2 on order item 16.', 16, '2025-03-11 05:46:35', 4, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #4 from user 2 on order item 29.', 29, '2025-06-03 05:46:35', 2, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #5 from user 2 on order item 75.', 75, '2025-03-11 05:46:35', 3, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #6 from user 2 on order item 41.', 41, '2025-05-02 05:46:35', 1, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #7 from user 2 on order item 92.', 92, '2025-04-04 05:46:35', 2, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #8 from user 2 on order item 65.', 65, '2025-05-02 05:46:35', 4, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #9 from user 2 on order item 91.', 91, '2025-02-28 05:46:35', 5, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #10 from user 2 on order item 91.', 91, '2025-05-10 05:46:35', 3, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #11 from user 2 on order item 50.', 50, '2025-05-13 05:46:35', 5, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #12 from user 2 on order item 72.', 72, '2025-03-26 05:46:35', 1, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #13 from user 2 on order item 63.', 63, '2025-05-29 05:46:35', 5, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #14 from user 2 on order item 64.', 64, '2025-03-19 05:46:35', 5, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #15 from user 2 on order item 37.', 37, '2025-03-30 05:46:35', 2, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #16 from user 2 on order item 66.', 66, '2025-05-06 05:46:35', 3, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #17 from user 2 on order item 63.', 63, '2025-04-18 05:46:35', 2, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #18 from user 2 on order item 62.', 62, '2025-06-02 05:46:35', 3, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #19 from user 2 on order item 100.', 100, '2025-05-01 05:46:35', 1, 1);
INSERT INTO Feedbacks (UserID, Content, OrderItemID, CreatedAt, Rate, Status) VALUES (2, N'Feedback #20 from user 2 on order item 75.', 75, '2025-05-17 05:46:35', 4, 1);

INSERT INTO Blogs_category (Bc_id, Bc_name)
VALUES
(1, 'Technology News'),
(2, 'User Guides'),
(3, 'Product Reviews'),
(4, 'Promotions & Events');

-- Dữ liệu mẫu chèn vào bảng Post
INSERT INTO Post (Post_id, Title, Author, Updated_date, Content, Bc_id, Thumbnail, Brief, Add_id) VALUES
(1, 'Introduction to Laptop Components', 'ThuongTN', GETDATE(),
'Laptops have become an essential part of modern life, used for work, education, entertainment, and communication.

To make informed decisions about purchasing, upgrading, or maintaining a laptop, it is important to understand its internal components and how they function together.

1. Central Processing Unit (CPU)
2. Graphics Processing Unit (GPU)
3. Random Access Memory (RAM)
4. Storage Drive (HDD or SSD)
5. Motherboard
6. Cooling System
7. Display
8. Battery
9. Input Devices
10. Connectivity and Ports
11. Audio and Webcam

Understanding these components empowers users to troubleshoot, upgrade, and optimize their laptops.', 
1, 'https://platform.labdoo.org/sites/default/files/content/laptop-wiki/laptop2.png', 
'Overview of key laptop components for beginners.', 1),

(2, 'Proper Laptop Cleaning and Maintenance Guide', 'ThuongTN', GETDATE(),
'Laptops collect dust and bacteria over time. This guide includes:
- Why maintenance matters
- Tools needed
- Cleaning steps for screen, keyboard, internals
- Maintenance schedule and hygiene tips', 
2, 'https://i.pcmag.com/imagery/articles/05uthAxgkcAOKdZzRzXfMvf-11..v1617741381.jpg', 
'Simple and effective laptop cleaning guide.', 2),

(3, 'Review of ASUS ROG Swift 27-inch Gaming Monitor', 'NinhTT', GETDATE(),
'Compare OLED and IPS gaming monitors in ASUS ROG lineup: PG27AQDM, PG27UQR, PG279Q, PG27UQ. Analyze refresh rate, HDR, G-Sync, contrast, and use cases.', 
3, 'https://i.rtings.com/assets/products/1kK1PmWU/asus-rog-swift-oled-pg27aqdm/design-medium.jpg?format=auto', 
'Detailed review of ASUS ROG Swift 27-inch gaming monitor.', 1),

(4, 'Optimizing Cooling Systems for Gaming PCs', 'ThuongTN', GETDATE(),
'Cooling tips:
- Improve airflow
- Apply thermal paste
- Adjust fan curves
- Clean regularly
- Monitor temperatures

Ideal temps: CPU < 85°C, GPU < 80°C.', 
2, 'https://www.intel.com/content/dam/www/central-libraries/us/en/images/opened-neon-lighted-computer-case-rwd.jpg', 
'Tips to optimize cooling systems for gaming PCs.', 1),

(5, 'PC Component Promotions for May 2025', 'NinhTT', GETDATE(),
'Deals on SSDs, CPUs, RAM, monitors, and prebuilt PCs from Dell, Newegg, Amazon. Highlights include Ryzen 7 7800X3D and Samsung 990 EVO Plus.', 
4, 'https://i0.wp.com/www.onthespotrepairs.com/wp-content/uploads/2024/05/Custom-Build-PC-Part-List-Late-2024.png?fit=1200%2C544&ssl=1', 
'Information on PC component promotions in May 2025.', 1),

(6, 'Step-by-Step RAM Installation Guide for Desktop PCs', 'NinhTT', GETDATE(),
'A safe and complete guide to install or upgrade RAM:
- Power off and ground yourself
- Open case
- Insert RAM properly
- Verify in BIOS and Windows', 
2, 'https://www.offtek.co.uk/images/pages/desktop-ram-installation.jpg', 
'Detailed guide on installing RAM for desktop PCs.', 2),

(7, 'SSD SATA vs NVMe: Which One Should You Choose', 'NinhTT', GETDATE(),
'Compare SATA and NVMe SSDs in terms of speed, compatibility, cost, and best use cases for gaming, content creation, and upgrading old PCs.', 
1, 'https://vstl.info/wp-content/uploads/2024/04/Purple-Minimalist-Develop-Your-Mobile-App-With-Us-Medium-Banner-1920-x-1080-px-1.jpg', 
'Comparing SATA and NVMe SSDs for faster computers.', 1),

(8, 'NVIDIA RTX 50 Series Graphics Card Launch Event', 'ThuongTN', GETDATE(),
'Highlights:
- RTX 5090, 5080, 5070 Ti launch
- Blackwell architecture
- DLSS 4 and Multi Frame Generation
- Laptop versions and stock issues', 
4, 'https://www.nvidia.com/content/dam/en-zz/Solutions/geforce/news/rtx-50-series-graphics-cards-gpu-laptop-announcements/geforce-rtx-5090-key-visual.jpg', 
'Highlights from the NVIDIA RTX 50 series graphics card launch event.', 1);

INSERT INTO OrderPreparements(UserID, OrderID, PrepareTime) values 
(2,1,GETDATE()), 
(2,2,GETDATE()), 
(2,3,GETDATE()), 
(2,4,GETDATE()), 
(2,5,GETDATE()), 
(2,6,GETDATE());

INSERT INTO Shipping(OrderID, ShipperID, ShippingStatus, ShipTime) values 
(1, 4, 'On going', GETDATE()), 
(2, 4, 'On going', GETDATE()), 
(3, 4, 'On going', GETDATE()), 
(4, 4, 'On going', GETDATE());

INSERT INTO Comments (Post_id, UserID, CommentText)
VALUES (1, 3, 'Very informative post, thank you!');

 INSERT INTO Users (RoleID, FullName, Email, PhoneNumber,  PasswordHash, CreatedAt, Status)
VALUES (3, 'LinhNV', 'customer@example.com', '0912345678', 'hashedpassword3', GETDATE(), 1);

-- Build_PC #1
INSERT INTO Build_PC (Price, Status) VALUES (18300000, 1);
-- Giả sử các CategoryID bên dưới thuộc ComponentID từ 2 đến 7 (MainBoard → CASE)
INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status) VALUES (1, 3, 2000000, 1, 1); -- MainBoard (ComponentID = 2)
INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status) VALUES (1, 17, 5000000, 2, 1); -- CPU (3)
INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status) VALUES (1, 6, 7000000, 3, 1); -- GPU (4)
INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status) VALUES (1, 103, 1500000, 4, 1); -- RAM (5)
INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status) VALUES (1, 79, 1800000, 5, 1); -- SSD (6)
INSERT INTO Build_PC_Items (BuildPCID, CategoryID, Price, WarrantyDetailID, Status) VALUES (1, 2, 1000000, 6, 1); -- CASE (7)

UPDATE Orders
SET Product_Type = 0;


-- Notification
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Đơn hàng mới', N'Bạn có đơn hàng mới cần xử lý.', 0, '2025-06-03 08:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Phản hồi khách hàng', N'Khách hàng vừa gửi feedback mới.', 0, '2025-06-03 09:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Báo cáo doanh thu', N'Báo cáo doanh thu tháng 6 đã sẵn sàng.', 0, '2025-06-03 10:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Kho hàng', N'Một số sản phẩm sắp hết hàng.', 0, '2025-06-03 11:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Đăng ký mới', N'Có người dùng mới đăng ký tài khoản.', 0, '2025-06-03 12:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Đơn hàng bị hủy', N'Một đơn hàng vừa bị hủy.', 0, '2025-06-03 13:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Bảo trì hệ thống', N'Hệ thống sẽ bảo trì vào 23:00 hôm nay.', 0, '2025-06-03 14:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Khuyến mãi mới', N'Chương trình khuyến mãi mới đã bắt đầu.', 0, '2025-06-03 15:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Phản hồi đã trả lời', N'Bạn vừa trả lời một feedback của khách.', 0, '2025-06-03 16:00:00');
INSERT INTO Notifications (UserID, SenderID, Title, Message, IsRead, CreatedAt) VALUES (1, 1, N'Đơn hàng giao thành công', N'Một đơn hàng đã giao thành công.', 0, '2025-06-03 17:00:00');
