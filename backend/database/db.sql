CREATE DATABASE IF NOT EXISTS urban_cut;

USE urban_cut;

CREATE TABLE IF NOT EXISTS clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW() ON UPDATE NOW()
);

CREATE TABLE IF NOT EXISTS barbeiros (
    id_barbeiro INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    id_barbearia INT,
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW() ON UPDATE NOW()
);

CREATE TABLE IF NOT EXISTS barbearias (
    id_barbearia INT AUTO_INCREMENT PRIMARY KEY,
    id_dono INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    url_endereco TEXT NOT NULL,
    tempo_medio TIME NOT NULL,
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW() ON UPDATE NOW()
);

CREATE TABLE IF NOT EXISTS dias_funcionamento (
    id_dia_funcionamento INT AUTO_INCREMENT,
    id_barbearia INT NOT NULL,
    dia_semana ENUM('Domingo','Segunda-Feira','Terça-Feira','Quarta-Feira','Quinta-Feira','Sexta-Feira','Sábado') NOT NULL,
    hora_abertura TIME NOT NULL,
    hora_fechamento TIME NOT NULL,
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW() ON UPDATE NOW(),
    PRIMARY KEY (id_dia_funcionamento,id_barbearia)
);

CREATE TABLE IF NOT EXISTS atendimentos (
    id_atendimento INT AUTO_INCREMENT PRIMARY KEY,
    id_barbearia INT NOT NULL,
    id_barbeiro INT NOT NULL,
    id_cliente INT NOT NULL,
    atendimento DATETIME NOT NULL,
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW() ON UPDATE NOW()
);

ALTER TABLE barbeiros ADD CONSTRAINT FOREIGN KEY (id_barbearia) REFERENCES barbearias(id_barbearia);

ALTER TABLE barbearias ADD CONSTRAINT FOREIGN KEY (id_dono) REFERENCES barbeiros(id_barbeiro);

ALTER TABLE dias_funcionamento ADD CONSTRAINT FOREIGN KEY (id_barbearia) REFERENCES barbearias(id_barbearia);

ALTER TABLE atendimentos ADD CONSTRAINT FOREIGN KEY (id_barbearia) REFERENCES barbearias(id_barbearia);
ALTER TABLE atendimentos ADD CONSTRAINT FOREIGN KEY (id_barbeiro) REFERENCES barbeiros(id_barbeiro);
ALTER TABLE atendimentos ADD CONSTRAINT FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente);

CREATE TABLE IF NOT EXISTS horarios_bloqueados (
    id_horario_bloqueado INT AUTO_INCREMENT PRIMARY KEY,
    id_barbeiro INT NOT NULL,
    inicio TIME NOT NULL,
    fim TIME NOT NULL,
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW() ON UPDATE NOW()
);

ALTER TABLE horarios_bloqueados ADD CONSTRAINT FOREIGN KEY (id_barbeiro) REFERENCES barbeiros(id_barbeiro);