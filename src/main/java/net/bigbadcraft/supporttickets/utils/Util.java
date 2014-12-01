package main.java.net.bigbadcraft.supporttickets.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class Util {

	private static final String B = ChatColor.DARK_AQUA;
	private static final String W = ChatColor.WHITE;
	private static final String R = ChatColor.RED;
	
	/**
	 * Send a player message to player
	 * @param player player to send to
	 * @param message message to send
	 */
	public static void msg(MC_Player player, String message) {
		player.sendMessage(message);
	}

	/**
	 * Log plugin's messages to console
	 * @param message message to log
	 */
	public static void log(String message) {
		System.out.println("[SupportTickets] " + message);
	}

	/**
	 * Log plugin's message with type to console
	 * @param level the type of message
	 * @param message the message to send
	 */
	public static void log(DebugLevel level, String message) {
		System.out.println("[SupportTickets] (" + level + ") - " + message);
	}

	/**
	 * Create a new directory
	 * @param f the file to create
	 */
	public static void makeDir(File f) {
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	
	/**
	 * Check if specified status is valid
	 * @param ticketStatus the status to validate
	 * @return if the status is valid
	 */
	public static boolean isValidStatus(String ticketStatus) {
		EnumSet<TicketStatus> status = EnumSet.of(TicketStatus.CLOSED,
				TicketStatus.OPEN, TicketStatus.PENDING, TicketStatus.REOPENED);
		List<String> temp = new ArrayList<String>();
		for (TicketStatus ts : status) {
			temp.add(ts.toString());
		}
		return temp.contains(ticketStatus);
	}

	/**
	 * Create a new file
	 * @param f the file to create
	 */
	public static void makeFile(File f) {
		if (!f.exists()) {
			try {
				f.createNewFile();
				log(DebugLevel.LOW, f.getName() + " successfully created.");
			} catch (IOException e) {
				log(DebugLevel.HIGH, f.getName() + " could not be created.");
				e.printStackTrace(); // So they notice
			}
		}
	}

	/**
	 * Check if player acquires permission
	 * @param player the player to permit
	 * @param perm the permission they require
	 * @return true if player has specified permission
	 */
	public static boolean checkPermission(MC_Player player, Permission perm) {
		if (!player.hasPermission(perm.toString())) {
			msg(player, R + "You do have the required permission: " + perm.toString());
			return false;
		}
		return true;
	}

	/**
	 * Convert an array of strings to a string
	 * @param startingIndex of the first array argument
	 * @param args the array you wish to convert
	 * @return the string array as a string
	 */
	public static String join(int startingIndex, String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			if (i >= startingIndex) {
				sb.append(i == args.length ? args[i] : args[i] + " ");
			}
		}
		return sb.toString();
	}

	/**
	 * Check if a string is a number
	 * @param s the string to check
	 * @return true if string is a number
	 */
	public static boolean isNumber(String s) {
		int value = Util.parseInt(s);
		if (value > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Convert a string to an integer
	 * @param s the string to convert
	 * @return value parse from string
	 */
	public static int parseInt(String s) {
		int value = 0;
		try {
			return value = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return value;
		}
	}

	/**
	 * Send player command menu
	 * @param player the player to send to
	 */
	public static void sendHelpMenu(MC_Player player) {
		msg(player, "Here are the " + B + "available" + W + " commands.");
		for (Permission permission : getPluginPermissions()) {
			if (player.hasPermission(permission.toString())) {
				msg(player, B + permission.getSyntax() + W + " - " + permission.getDescription());
			}
		}
	}
	
	/**
	 * Retrieve all plugin's permission into a EnumSet
	 * @return perms a list pertaining all permission enumerations
	 */
	public static EnumSet<Permission> getPluginPermissions() {
		EnumSet<Permission> perms = EnumSet.of(Permission.PLAYER_USE,
				Permission.PLAYER_REQUEST, Permission.PLAYER_CHECK,
				Permission.MODERATOR_SELECT, Permission.MODERATOR_LIST,
				Permission.MODERATOR_CHANGE, Permission.ADMIN_PROGRESS_CHECK,
				Permission.ADMIN_OVERRIDE);
		return perms;
	}

}
