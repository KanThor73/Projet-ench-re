package Util;

import org.mindrot.jbcrypt.BCrypt;

public class Hashing {

	public static String[] hashPassword(String password) {

		String salt = BCrypt.gensalt();// generer un sel pour le hashing

		String hashedPassword = BCrypt.hashpw(password, salt); // hasher le mdps

		return new String[] { hashedPassword, salt };
	}

	public static boolean ckeckPassword(String hashedPasswordBDD, String UserPassword) {// renvoie true si le mdp et le meme que dans la bdd

		boolean isOk = false;

		if (BCrypt.checkpw(UserPassword, hashedPasswordBDD)) {// compare les deux mdps
			isOk = true;
		}else {
			isOk = false;
		}
		return isOk;
	}
	
	public static String hashPasswordToCompare(String UserPassword, String UserSalt) {// renvoie true si le mdp et le meme que dans la bdd

		String newHash = BCrypt.hashpw(UserPassword, UserSalt);

		return newHash;
	}
}
