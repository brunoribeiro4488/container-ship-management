package lapr.project.utils.bst;

import lapr.project.model.Port;

import java.util.Comparator;
import java.util.List;

public class KD_Tree_Port extends KD_Tree {

    private final Comparator<Port> cmpX = new Comparator <Port>(){
        @Override
        public int compare(Port p1, Port p2) {
            return Double.compare(Double.parseDouble(p1.getLongitude()),Double.parseDouble(p2.getLongitude()));
        }
    };

    private final Comparator <Port> cmpY = new Comparator <Port>(){
        @Override
        public int compare(Port p1, Port p2) {
            return Double.compare(Double.parseDouble( p1.getLatitude()), Double.parseDouble( p2.getLatitude()));
        }
    };

    void swap(List<Port> portList, int i, int j)
    {
        Port temp = portList.get(i);
        portList.set(i, portList.get(j));
        portList.set( j , temp);
    }

    int partition(List<Port> portList, int low, int high,boolean divX)
    {

        Port pivot = portList.get( high);

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {
            int cmpResult = (divX ? cmpX : cmpY).compare(portList.get(j), pivot);
            if (cmpResult < 0)
            {
                i++;
                swap(portList, i, j);
            }
        }
        swap(portList, i + 1, high);
        return (i + 1);
    }


    public void quickSort(List<Port> portList, int low, int high,boolean divX)
    {
        if (low < high)
        {

            int pi = partition(portList, low, high,divX);

            Port port = portList.get(pi/2);
            super.insert(Double.parseDouble(port.getLongitude()),Double.parseDouble(port.getLatitude()),port);

            quickSort(portList, low, pi - 1,!divX);
            quickSort(portList, pi + 1, high,!divX);
        }
    }



    public void insertAllPorts(List<Port> portList){
        portList.sort(cmpX);
        int size = portList.size();
        Port port = portList.get(size/2);
        super.insert(Double.parseDouble(port.getLongitude()),Double.parseDouble(port.getLatitude()),port);

        quickSort(portList,0,size-1,true);

        for (Port ports: portList) {
            super.insert(Double.parseDouble(ports.getLongitude()),Double.parseDouble(ports.getLatitude()),ports);
        }
    }




}

