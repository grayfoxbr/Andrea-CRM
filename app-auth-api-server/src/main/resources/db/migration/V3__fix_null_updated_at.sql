-- Corrige registros de usuário cujo updatedat ficou nulo, pois o @PrePersist
-- da entidade User originalmente só preenchia createdat na criação
-- (updatedat era setado apenas no @PreUpdate, ou seja, só em updates).
-- Isso quebrava o OidcUserInfoService.loadUser() ao gerar o ID token
-- para usuários cadastrados via POST /users.
UPDATE users
SET updatedat = createdat
WHERE updatedat IS NULL;