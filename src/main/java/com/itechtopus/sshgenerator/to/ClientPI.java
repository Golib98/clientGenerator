package com.itechtopus.sshgenerator.to;

import com.itechtopus.sshgenerator.generator.Util;
import com.itechtopus.sshgenerator.model.Client;
import com.itechtopus.sshgenerator.model.ClientAddr;
import com.itechtopus.sshgenerator.model.ClientPhone;
import com.itechtopus.sshgenerator.model.ModelParent;
import com.itechtopus.sshgenerator.model.enums.AddressType;

import java.util.List;
import java.util.Map;

// Client Particular Info

public class ClientPI extends ModelParent {

  public Client client;

  public Map<AddressType, ClientAddr> addresses = Util.newMap();

  public List<ClientPhone> phones = Util.newList();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ClientPI clientPI = (ClientPI) o;

    if (client != null ? !client.equals(clientPI.client) : clientPI.client != null) return false;
    if (addresses != null ? !addresses.equals(clientPI.addresses) : clientPI.addresses != null) return false;
    return phones != null ? phones.equals(clientPI.phones) : clientPI.phones == null;
  }

  @Override
  public int hashCode() {
    int result = client != null ? client.hashCode() : 0;
    result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
    result = 31 * result + (phones != null ? phones.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ClientPI{" + client.surname +
        " " + client.name.charAt(0) +
        ". " + client.patronymic.charAt(0)+
        '}';
  }
}
