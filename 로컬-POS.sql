ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

CREATE USER POS IDENTIFIED BY 1234  -- ����� ID : POS, ��й�ȣ : 1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP;
GRANT connect, resource, dba TO POS; -- ���� �ο�

DROP TABLE ��ǰ;
DROP TABLE ��;
DROP TABLE ������;


--��ǰ ���̺�
CREATE TABLE ��ǰ(	
    ��ǰ�� VARCHAR2(60) NOT NULL, 
	���� NUMBER, 
    ī�װ� VARCHAR2(60) NOT NULL
); 

--�� ���̺�
CREATE TABLE ��(
    �ƾƵ� VARCHAR2(60) NOT NULL,
    �̸�  VARCHAR2(60) NOT NULL,
    ����Ʈ NUMBER
);

--������ ���̺�
CREATE TABLE ������(
    ��й�ȣ VARCHAR(40) NOT NULL
);

-- [��ǰ ���̺� Ʃ�� ����]
INSERT INTO ��ǰ VALUES ('����', 2000, '������ǰ');
INSERT INTO ��ǰ VALUES ('�κ�', 2200, '������ǰ');
INSERT INTO ��ǰ VALUES ('�', 4500, '������ǰ');
INSERT INTO ��ǰ VALUES ('����Ʈ��', 3500, '������ǰ');
INSERT INTO ��ǰ VALUES ('�Ļ�', 3000, '������ǰ');
INSERT INTO ��ǰ VALUES ('�Ŷ��', 600, '������ǰ');
INSERT INTO ��ǰ VALUES ('�ܹ���', 1400, '������ǰ');
INSERT INTO ��ǰ VALUES ('�Ը���', 5000, '������ǰ');
INSERT INTO ��ǰ VALUES ('������', 6500, '������ǰ');
INSERT INTO ��ǰ VALUES ('����', 6300, '������ǰ');

INSERT INTO ��ǰ VALUES ('������', 2000, '���ڷ�');
INSERT INTO ��ǰ VALUES ('����Ĩ', 2300, '���ڷ�');
INSERT INTO ��ǰ VALUES ('��Ĩ', 1500, '���ڷ�');
INSERT INTO ��ǰ VALUES ('������', 2600, '���ڷ�');
INSERT INTO ��ǰ VALUES ('������', 800, '���ڷ�');

INSERT INTO ��ǰ VALUES ('���̽�', 2500, '�ַ�');
INSERT INTO ��ǰ VALUES ('ī��', 1300, '�ַ�');
INSERT INTO ��ǰ VALUES ('ó��ó��', 1500, '�ַ�');
INSERT INTO ��ǰ VALUES ('���̳���', 1400, '�ַ�');
INSERT INTO ��ǰ VALUES ('��׽�', 2000, '�ַ�');

INSERT INTO ��ǰ VALUES ('����Ƽ��', 1500, '��ȭ');
INSERT INTO ��ǰ VALUES ('����', 1000, '��ȭ');
INSERT INTO ��ǰ VALUES ('�縻', 2200, '��ȭ');
INSERT INTO ��ǰ VALUES ('���÷�', 5000, '��ȭ');
INSERT INTO ��ǰ VALUES ('��Ʈ', 1500, '��ȭ');

INSERT INTO ��ǰ VALUES ('����', 5000, '���');
INSERT INTO ��ǰ VALUES ('������', 4500, '���');
INSERT INTO ��ǰ VALUES ('�����ڽ�', 6500, '���');
INSERT INTO ��ǰ VALUES ('�ȸ��Ƹ�Ʈ', 5000, '���');
INSERT INTO ��ǰ VALUES ('��ũ', 5500, '���');

-- [�� ���̺� Ʃ�� ����]
INSERT INTO �� VALUES ('morri', '������', 5000);
INSERT INTO �� VALUES ('swine', '�ֿ�', 3000);
INSERT INTO �� VALUES ('julin', '������', 4000);
INSERT INTO �� VALUES ('joete', '�ȼ���', 10000);
INSERT INTO �� VALUES ('gudre', 'Ȳ����', 2000);
INSERT INTO �� VALUES ('allos', '��âȣ', 1000);

-- [������ ���̺� Ʃ�� ����]
INSERT INTO ������ VALUES ('1234');

COMMIT;
