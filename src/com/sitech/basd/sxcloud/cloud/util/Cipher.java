package com.sitech.basd.sxcloud.cloud.util;

/*
 * This file is part of "The Java Telnet Application".
 *
 * (c) Matthias L. Jugel, Marcus Mei遪er 1996-2002. All Rights Reserved.
 *
 * Please visit http://javatelnet.org/ for updates and contact.
 *
 * --LICENSE NOTICE--
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * --LICENSE NOTICE--
 */

/**
 * Cipher class is the type for all other ciphers.
 * @author Marcus Meissner
 * @version $Id: Cipher.java,v 1.1 2012/06/15 01:44:04 duangh Exp $
 */

public abstract class Cipher {

  public static Cipher getInstance(String algorithm) {
    Class c;
    try {
      c = Class.forName("com.sitech.util." + algorithm);
      return (Cipher) c.newInstance();
    } catch (Throwable t) {
      System.err.println("Cipher: unable to load instance of '" + algorithm + "'");
      return null;
    }
  }

  /**
   * Encrypt source byte array using the instantiated algorithm.
   */
  public byte[] encrypt(byte[] src) {
    byte[] dest = new byte[src.length];
    encrypt(src, 0, dest, 0, src.length);
    return dest;
  }

  /**
   * The actual encryption takes place here.
   */
  public abstract void encrypt(byte[] src, int srcOff, byte[] dest, int destOff, int len);

  /**
   * Decrypt source byte array using the instantiated algorithm.
   */
  public byte[] decrypt(byte[] src) {
    byte[] dest = new byte[src.length];
    decrypt(src, 0, dest, 0, src.length);
    return dest;
  }

  /**
   * The actual decryption takes place here.
   */
  public abstract void decrypt(byte[] src, int srcOff, byte[] dest, int destOff, int len);

  public abstract void setKey(byte[] key);

  public void setKey(String key) {
    setKey(key.getBytes());
  }
}
