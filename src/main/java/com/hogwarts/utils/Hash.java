package com.hogwarts.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
//    Gera hash de senha
    public static String hashSenha(String senha) throws NoSuchAlgorithmException {
//        Cria objeto MessageDigest com SHA-256
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

//        Gera hash em bytes e converte para número
        byte[] hashBytes = sha256.digest(senha.getBytes());
        BigInteger numHash = new BigInteger(1, hashBytes);

//        retorno em hexadecimal
        return numHash.toString(16);
    }

//    Compara senha digitada com senha do banco
    public static boolean comparaSenhas(String senha, String senhaHash) throws NoSuchAlgorithmException{
        return hashSenha(senha).equals(senhaHash);
    }
}