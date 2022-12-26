package Inhwan.week15;

class 추석트래픽 {
    public int solution(String[] lines) {
        int l = lines.length;

        Process[] processes = new Process[l];
        for (int i = 0; i < l; i++) {
            processes[i] = stringToProcess(lines[i]);
        }

        int answer = 0;

        for (Process process : processes) {
            int c1 = 0;
            int c2 = 0;

            Process p1 = new Process(process.start, process.start + 999);
            Process p2 = new Process(process.end, process.end + 999);

            for (Process process2 : processes) {
                if (intersect(p1, process2)) c1++;
                if (intersect(p2, process2)) c2++;
            }

            int c = Math.max(c1, c2);
            answer = Math.max(c, answer);
        }

        return answer;
    }
    class Process {
        int start;
        int end;

        Process(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    boolean intersect(Process p1, Process p2) {
        if (p1.end < p2.start) return false;
        if (p2.end < p1.start) return false;

        return true;
    }

    Process stringToProcess(String str) {
        int l = str.length();

        int h = Integer.parseInt(str.substring(11,13));
        int m = Integer.parseInt(str.substring(14,16));
        int s = Integer.parseInt(str.substring(17,19));
        int ms = Integer.parseInt(str.substring(20,23));

        int endTime = ms + 1000*s + 60000*m + 3600000*h;

        double Dt = Double.parseDouble(str.substring(24, l-1));
        Dt *= 1000;
        int dt = (int) Dt;
        dt--;

        return new Process(endTime - dt, endTime);
    }
}