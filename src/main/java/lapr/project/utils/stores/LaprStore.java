package lapr.project.utils.stores;


public class LaprStore {

    private static final Double MASSCONTAINER = 500.0;
    private static final Double DENSIDADE = 1030.0;
    private static final Double G = 9.8;
    private static final Double PANAMAXMASS = 20000000000.0;
    private static final Double NEWPANAMAXMASS = 50000000000.0;
    private static final Double ULTRALARGECONTAINERVESSELMASS = 100000000000.0;


    public LaprStore(){
        // It is not needed to inicialize anything
    }


    public Double getTotalMass(int nContainers) {
        return nContainers * MASSCONTAINER;
    }

    public Double getHeight(Double width, Double length, Double totalMass) {
        Double volume = totalMass/DENSIDADE;
        return volume/(width*length);
    }

    public Double getPressure(Double height) {
        return DENSIDADE*G*height;
    }

    public String getTypeOfVessel(int numberOfContainers){
        if(numberOfContainers <= 5100){
            return String.format("The best ship for the task is a Panamax.\nThis ship can transport up to 5100 containers.\nThe dimensions of this ship are 294x32x12 (m).\nWith tower in the stern\n");
        }else if(numberOfContainers > 5100 && numberOfContainers <= 14500){
            return String.format("The best ship for the task is a New-Panamax.\nThis ship can transport up to 10000 containers.\nThe dimensions of this ship are 366x49x15 (m).\nWith tower in the middle\n");
        }else{
            return String.format("The best ship for the task is a Ultra Large Container Vessel.\nThis ship has capacity for more than 14500 containers.\nThis ship is bigger than 366x49x15 (m).\nWith tower in the bow\n");
        }
    }

    /**
     * Gets the center of mass of the ship (x,y)
     * @param shipmass Massa do navio
     * @param tamanho tamanho
     * @return the center of mass of the ship (x,y)
     */
    public String getCenterOfMass(double shipmass,int tamanho){
        double pesocontrol = shipmass / tamanho;
        int xship = tamanho/2;
        int yship = tamanho/2;

        int xcontrol;
        int ycontrol;

        if (tamanho == 80) {
            xcontrol = 0;
            ycontrol = tamanho / 2;
        }else if (tamanho ==100){
            xcontrol = tamanho /2;
            ycontrol = tamanho / 2;
        }else {
            xcontrol = tamanho-1;
            ycontrol = tamanho / 2;
        }

        double xmassa = ((shipmass*xship + pesocontrol*xcontrol) / (shipmass+pesocontrol));
        double ymassa =  ((shipmass*yship + pesocontrol*ycontrol) / (shipmass+pesocontrol));

        return String.format("(%f,%f)",xmassa,ymassa);
    }

    public String printMatrix (String[][] m,String cm) {
        try {
            cm = cm.replaceAll("[().]","");
            for (int i = 0; i <m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    m[i][j] ="0";
                }
            }
            String[] aux = cm.split(",");
            m[Integer.parseInt(aux[0])][Integer.parseInt(aux[2])]= "x";

            int rows = m.length;
            int columns = m[0].length;
            StringBuilder str= new StringBuilder();

            for (int i = rows-1; i >= 0; i--) {
                str.append("|\t");
                for (int j = columns-1; j >= 0; j--) {
                    str.append(m[i][j]).append("\t");
                }
                str.append("|\n");
            }
            return str.toString();
        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }
        return null;
    }

    public String getCenterOfMassPanamax(){
        String[][] arr = new String[80][80] ;
        String aux = getCenterOfMass(PANAMAXMASS,80);
        String str = String.format("O centro de massa encontra-se em %s",aux);
        return String.format("%s\n%s",str ,printMatrix(arr,aux));
    }

    public String getCenterOfMassNewPanamax(){
        String[][] arr = new String[100][100] ;
        String aux = getCenterOfMass(NEWPANAMAXMASS,100);
        String str = String.format("O centro de massa encontra-se em %s",aux);
        return String.format("%s\n%s",str ,printMatrix(arr,aux));
    }

    public String getCenterOfMassUltraLargeVessel(){
        String[][] arr = new String[200][200] ;
        String aux = getCenterOfMass(ULTRALARGECONTAINERVESSELMASS,200);
        String str = String.format("O centro de massa encontra-se em %s",aux);
        return String.format("%s\n%s",str ,printMatrix(arr,aux));
    }

    public String keepCenterOfMassPanamax ( int containers){
        String arr[][] = new String[80][80];
        String aux = getCenterOfMass(PANAMAXMASS, 80);
        String str = String.format("O centro de massa encontra-se em %s", aux);
        return String.format("%s\n%s", str, addContainersMatrix(arr, aux, containers));
    }

    public String keepCenterOfMassNewPanamax ( int containers){
        String arr[][] = new String[100][100];
        String aux = getCenterOfMass(NEWPANAMAXMASS, 100);
        String str = String.format("O centro de massa encontra-se em %s", aux);
        return String.format("%s\n%s", str, addContainersMatrix(arr, aux, containers));
    }

    public String keepCenterOfMassUltraLargeVessel ( int containers){
        String arr[][] = new String[200][200];
        String aux = getCenterOfMass(ULTRALARGECONTAINERVESSELMASS, 200);
        String str = String.format("O centro de massa encontra-se em %s", aux);
        return String.format("%s\n%s", str, addContainersMatrix(arr, aux, containers));
    }

    public String addContainersMatrix (String[][]m, String cm,int containers){
        try {
            int cont = 0;
            int d = 0;
            int c = 0;
            cm = cm.replaceAll("[().]", "");
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    m[i][j] = "0";
                }
            }
            String aux[] = cm.split(",");
            int centerx = Integer.parseInt(aux[0]);
            int centery = Integer.parseInt(aux[2]);
            m[centerx][centery] = "x";

            while (containers >= 2 && d == 0) {
                cont++;
                for (int x = 1; x < m.length; x++) {
                    if (centerx - x >= 0 && centery - x >= 0 && centerx + x < m.length && centery + x < m[0].length) {

                        if (containers - 2 >= 0) {
                            m[centerx - x][centery - x] = String.valueOf(cont);
                            m[centerx + x][centery + x] = String.valueOf(cont);
                            containers = containers - 2;
                        }

                        if (containers - 2 >= 0) {
                            m[centerx][centery + x] = String.valueOf(cont);
                            m[centerx][centery - x] = String.valueOf(cont);
                            containers = containers - 2;
                        }

                        if (containers - 2 >= 0) {
                            m[centerx - x][centery] = String.valueOf(cont);
                            m[centerx + x][centery] = String.valueOf(cont);
                            containers = containers - 2;
                        }

                        if (containers - 2 >= 0) {
                            m[centerx + x][centery - x] = String.valueOf(cont);
                            m[centerx - x][centery + x] = String.valueOf(cont);
                            containers = containers - 2;
                        }
                        for (int j = 0; j < x; j++) {
                            if (containers - 2 >= 0) {
                                m[centerx - x + j][centery - x] = String.valueOf(cont);
                                m[centerx + x - j][centery + x] = String.valueOf(cont);
                                containers = containers - 2;
                            }

                            if (containers - 2 >= 0) {
                                m[centerx - x][centery + j] = String.valueOf(cont);
                                m[centerx + x][centery - j] = String.valueOf(cont);
                                containers = containers - 2;
                            }

                            if (containers - 2 >= 0) {
                                m[centerx - x][centery - j] = String.valueOf(cont);
                                m[centerx + x][centery + j] = String.valueOf(cont);
                                containers = containers - 2;
                            }

                            if (containers - 2 >= 0) {
                                m[centerx + x - j][centery - x] = String.valueOf(cont);
                                m[centerx - x + j][centery + x] = String.valueOf(cont);
                                containers = containers - 2;
                            }
                        }
                        c++;
                    } else {
                        if (c == 0) {
                            d++;
                        }
                    }

                }
            }

            if (containers > 0) {
                m[centerx][centery] = "x-" + containers;
            }

            int rows = m.length;
            int columns = m[0].length;
            String str = "";

            for (int i = rows - 1; i >= 0; i--) {
                str += "|\t";
                for (int j = columns - 1; j >= 0; j--) {
                    str += m[i][j] + "\t";
                }
                str += "|\n";
            }
            return str;
        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }
        return null;
    }
}