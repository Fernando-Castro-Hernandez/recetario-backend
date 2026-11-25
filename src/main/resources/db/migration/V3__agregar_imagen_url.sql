-- V3: foto de la receta. La app sube la imagen a Supabase Storage y aquí se guarda su URL pública.
-- V1 y V2 ya corrieron en la base real: los cambios nuevos siempre van en un script nuevo.

ALTER TABLE recetas ADD COLUMN imagen_url VARCHAR(500); -- opcional: una receta puede no tener foto
