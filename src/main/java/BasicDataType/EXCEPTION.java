package BasicDataType;

import VirtualMachine.Namespace;

public class EXCEPTION extends CLASS{

    public EXCEPTION(String name, long UUID, Namespace father, Namespace root, int size) {
        super(name, UUID, father, root, size);
    }

    public EXCEPTION(STR name,long UUID, Namespace father, Namespace root, int size){
        super(name, UUID, father, root, size);
    }

    @Override
    public String toString() {
        return "EXCEPTION{" +
                "name=" + name +
                ", namespace=" + namespace() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EXCEPTION exception = (EXCEPTION) o;

        return namespace() != null ? namespace().equals(exception.namespace()) : exception.namespace() == null;
    }
}
