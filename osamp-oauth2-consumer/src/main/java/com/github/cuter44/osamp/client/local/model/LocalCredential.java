package com.github.cuter44.osamp.client.local.model;

public class LocalCredential
{
  // FIELDS
    public Long id;

    public String principal;

  // ACCESSORS
    public Long getId()
    {
        return(this.id);
    }

    public void setId(Long id)
    {
        this.id = id;

        return;
    }

    public String getPrincipal()
    {
        return(this.principal);
    }

    public void setPrincipal(String principal)
    {
        this.principal = principal;

        return;
    }

  // CONSTRUCTORS
    public LocalCredential()
    {
        // NOOP
        return;
    }

    public LocalCredential(String principal)
    {
        this();

        this.setPrincipal(principal);

        return;
    }

  // EQUALITY
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int hash = 17;

        hash = prime * hash + ((id == null) ? 0 : id.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o==null || !this.getClass().equals(o.getClass()))
            return false;

        LocalCredential s = (LocalCredential) o;

        return(
            (
                true
            )
            &&
            (
                (this.id == s.id) ||
                (this.id != null && this.id.equals(s.id))
            )
        );
    }

  // TO STRING
    @Override
    public String toString()
    {
        return(
            String.format(
                "%s#%d@%s",
                this.getClass().getSimpleName(),
                this.getId(),
                this.getPrincipal()
            )
        );
    }
}
