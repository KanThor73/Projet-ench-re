package Util;

import org.mindrot.jbcrypt.BCrypt;

public class Hashing {

	public static String[] hashPassword(String password) {

		String salt = BCrypt.gensalt();// generer un sel pour le hashing

		String hashedPassword = BCrypt.hashpw(password, salt); // hasher le mdps

		return new String[] { hashedPassword, salt };
	}

	public static boolean ckeckPassword(String hashedPasswordBDD, String UserPassword, String UserSalt) {// renvoie true si le mdp et le meme que dans la bdd

		boolean isOk = false;
		String newHash = BCrypt.hashpw(UserPassword, UserSalt);

		if (BCrypt.checkpw(newHash, hashedPasswordBDD)) {// compare les deux mdps
			isOk = true;
		} else {
			return isOk;
		}
		return isOk;

	}
	
	public static String hashPasswordToCompare(String UserPassword, String UserSalt) {// renvoie true si le mdp et le meme que dans la bdd

		String newHash = BCrypt.hashpw(UserPassword, UserSalt);

		return newHash;

	}

}
