create database VirtualArtGallery;

use VirtualArtGallery;

CREATE TABLE Artists ( 
ArtistID INT PRIMARY KEY, 
Name VARCHAR(255) NOT NULL, 
Biography TEXT, 
Nationality VARCHAR(100)); -- Create the Categories table 
CREATE TABLE Categories ( 
CategoryID INT PRIMARY KEY, 
Name VARCHAR(100) NOT NULL); -- Create the Artworks table 
CREATE TABLE Artworks ( 
ArtworkID INT PRIMARY KEY, 
Title VARCHAR(255) NOT NULL, 
ArtistID INT, 
CategoryID INT, 
Year INT, 
Description TEXT, 
ImageURL VARCHAR(255), 
FOREIGN KEY (ArtistID) REFERENCES Artists (ArtistID), 
FOREIGN KEY (CategoryID) REFERENCES Categories (CategoryID)); -- Create the Exhibitions table 
CREATE TABLE Exhibitions ( 
ExhibitionID INT PRIMARY KEY, 
Title VARCHAR(255) NOT NULL, 
StartDate DATE, 
EndDate DATE, 
Description TEXT); -- Create a table to associate artworks with exhibitions 
CREATE TABLE ExhibitionArtworks ( 
ExhibitionID INT, 
ArtworkID INT, 
PRIMARY KEY (ExhibitionID, ArtworkID), 
FOREIGN KEY (ExhibitionID) REFERENCES Exhibitions (ExhibitionID), 
FOREIGN KEY (ArtworkID) REFERENCES Artworks (ArtworkID));



-- Insert sample data into the Artists table 
INSERT INTO Artists (ArtistID, Name, Biography, Nationality) VALUES 
(1, 'Pablo Picasso', 'Renowned Spanish painter and sculptor.', 'Spanish'), 
(2, 'Vincent van Gogh', 'Dutch post-impressionist painter.', 'Dutch'), 
(3, 'Leonardo da Vinci', 'Italian polymath of the Renaissance.', 'Italian'); -- Insert sample data into the Categories table 
INSERT INTO Categories (CategoryID, Name) VALUES 
(1, 'Painting'), 
(2, 'Sculpture'), 
(3, 'Photography'); -- Insert sample data into the Artworks table 
INSERT INTO Artworks (ArtworkID, Title, ArtistID, CategoryID, Year, Description, ImageURL) VALUES 
(1, 'Starry Night', 2, 1, 1889, 'A famous painting by Vincent van Gogh.', 'starry_night.jpg'), 
(2, 'Mona Lisa', 3, 1, 1503, 'The iconic portrait by Leonardo da Vinci.', 'mona_lisa.jpg'), 
(3, 'Guernica', 1, 1, 1937, 'Pablo Picasso\'s powerful anti-war mural.', 'guernica.jpg'); -- Insert sample data into the Exhibitions table 
INSERT INTO Exhibitions (ExhibitionID, Title, StartDate, EndDate, Description) VALUES 
(1, 'Modern Art Masterpieces', '2023-01-01', '2023-03-01', 'A collection of modern art masterpieces.'), 
(2, 'Renaissance Art', '2023-04-01', '2023-06-01', 'A showcase of Renaissance art treasures.'); -- Insert artworks into exhibitions 
INSERT INTO ExhibitionArtworks (ExhibitionID, ArtworkID) VALUES 
(1, 1), 
(1, 2), 
(1, 3), 
(2, 2);


-- question 1 
select artists.name, count(artworks.artistid) as NUM
from artworks
join artists on artworks.artistid=artists.artistid
group by artists.artistid
order by NUM desc;

select title
from artworks
join artists
on artworks.artistid=artists.artistid
where artists.Nationality="Spanish" or artists.Nationality="Dutch"
order by artworks.year;

select artists.name, count(artworks.artistid)
from artworks
join artists
on artworks.artistid=artists.artistid
join Categories
on artworks.CategoryID=Categories.CategoryID
where Categories.name="Painting"
group by artists.name;


select artworks.title,artists.name,Categories.name
from exhibitionartworks
join artworks 
on exhibitionartworks.artworkid = artworks.artworkid
join artists 
on artworks.artistid = artists.artistid
join categories 
on artworks.categoryid = categories.categoryid
join exhibitions 
on exhibitionArtworks.exhibitionid = exhibitions.Exhibitionid
WHERE exhibitions.title = 'Modern Art Masterpieces';

select artists.name, count(artworks.artistid) 
from artworks
join artists
on artworks.artistid=artists.artistid
group by artists.artistid
having count(artworks.artistid)>1;

select artworks.title
from artworks
join exhibitionartworks ea1 on artworks.artworkid = ea1.artworkid
join exhibitions e1 on ea1.exhibitionid = e1.exhibitionid
join exhibitionartworks ea2 on artworks.artworkid = ea2.artworkid
join exhibitions e2 on ea2.exhibitionid = e2.exhibitionid
where e1.title = 'modern art masterpieces' and e2.title = 'renaissance art';

select categories.name, count(artworks.artworkid)
from artworks
join categories on artworks.categoryid = categories.categoryid
group by categories.name;

select artists.name, count(artworks.artistid) 
from artworks
join artists
on artworks.artistid=artists.artistid
group by artists.artistid
having count(artworks.artistid)>3;

select title
from artworks
join artists
on artworks.artistid=artists.artistid
where artists.Nationality="Spanish";

select exhibitions.title
from exhibitions
join exhibitionartworks ea1 on exhibitions.exhibitionid = ea1.exhibitionid
join artworks a1 on ea1.artworkid = a1.artworkid
join exhibitionartworks ea2 on exhibitions.exhibitionid = ea2.exhibitionid
join artworks a2 on ea2.artworkid = a2.artworkid
join artists art1 on a1.artistid = art1.artistid
join artists art2 on a2.artistid = art2.artistid
where art1.name = 'Vincent Van Gogh' and art2.name = 'Leonardo Da Vinci';


select artworks.Title
from artworks
where Artworks.artworkid not in (
select artworkid 
from ExhibitionArtworks);


select artists.name 
from artworks
join categories on artworks.categoryid=categories.categoryid
join artists on artworks.artistid=artists.artistid
where count(Artworks.artistid)=count(categories.name);

select categories.name, count(artworks.artworkid) 
from artworks
join categories on artworks.categoryid = categories.categoryid
group by categories.name;

 select artists.name, count(artworks.artistid)  

from artworks 

join artists 

on artworks.artistid=artists.artistid 

group by artists.artistid 

having count(artworks.artistid)>2; 


-- 16
select artworks.title
from artworks
join exhibitionartworks on artworks.artworkid = exhibitionartworks.artworkid
join exhibitions on exhibitionartworks.exhibitionid = exhibitions.exhibitionid
where exhibitions.title = 'modern art masterpieces';

select average(artworks.year)
from artworks;

-- 18

select title
from artworks
where artworkid not in (
  select artworkid
  from exhibitionartworks
);


-- 19
select artists.name
from artists
join artworks on artists.artistid = artworks.artistid
where artworks.categoryid = (
  select categoryid
  from artworks
  where title = 'mona lisa'
);

-- 20
select artists.name, count(artworks.artistid)
from artworks
join artists on artworks.artistid=artists.artistid
group by artists.name;
