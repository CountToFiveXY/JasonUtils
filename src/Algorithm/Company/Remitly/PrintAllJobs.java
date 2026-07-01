package Algorithm.Company.Remitly;

import java.util.*;

/**
 * Print All Jobs (with dependencies first)
 *
 * Given a list of jobs:
 * Each Job has:
 *   - name
 *   - cmd
 *   - deps (list of dependent Job objects)
 *
 * Requirement:
 * Print execution order so that:
 *   dependency cmds print first
 *   then current job cmd
 *
 * Example:
 *
 * A(cmdA)
 * ├── B(cmdB)
 * │   └── D(cmdD)
 * └── C(cmdC)
 *
 * Output:
 * cmdD
 * cmdB
 * cmdC
 * cmdA
 *
 * Hint:
 * Post-order DFS
 *
 * Follow-up:
 * 1. Shared dependency only print once
 * 2. Detect cycle
 * 3. Return List<String> instead of print
 */
public class PrintAllJobs {

    static class Job {
        String name;
        String cmd;
        List<Job> deps;

        Job(String name, String cmd) {
            this.name = name;
            this.cmd = cmd;
            this.deps = new ArrayList<>();
        }

        void addDep(Job job) {
            deps.add(job);
        }
    }

    public static void printJobs(List<Job> jobs) {
        //TODO
        HashSet<String> visited = new HashSet<>();
        for (Job job: jobs) {
            dfs(job, visited);
        }
    }

    private static void dfs(Job job, HashSet<String> visited) {
        if (job == null || visited.contains(job.name)) {
            return;
        }
        for (Job child: job.deps) {
            dfs(child, visited);
        }
        System.out.println(String.format("%s", job.cmd));
        visited.add(job.name);
     }


    public static void printJob(List<Job> jobs) {
        // TODO:
        HashMap<String, List<Job>> dependency = new HashMap<>();
        HashMap<String, Integer> degrees = new HashMap<>();

        for (Job job: jobs) {
            degrees.put(job.name, job.deps == null? 0 : job.deps.size());
            if (job.deps != null) {
                for (Job dep : job.deps) {
                    dependency.computeIfAbsent(dep.name, k -> new ArrayList<>()).add(job);
                }
            }
        }

        System.out.println(String.format("%s", degrees ));

        Queue<Job> q = new LinkedList<>();

        for (Job job: jobs) {
            if (degrees.get(job.name) == 0) {
                q.offer(job);
            }
        }
        System.out.println(String.format("q size: %s",q.size() ));

        while(!q.isEmpty()) {
            Job curr = q.poll();
            System.out.println(String.format("%s", curr.cmd));

            if (dependency.containsKey(curr.name)) {
                List<Job> list = dependency.get(curr.name);

                for (Job job: list) {
                    int currDegree = degrees.get(job.name);
                    if (currDegree == 1) {
                        q.offer(job);
                        degrees.remove(job.name);
                    } else {
                        degrees.put(job.name, currDegree - 1);
                    }
                }
            }
        }

    }

    private static void dfs(Job job, Set<Job> visited) {
        // TODO:
        // base case
        // visit all deps first
        // print current cmd
    }

    public static void main(String[] args) {

        Job a = new Job("A", "cmdA");
        Job b = new Job("B", "cmdB");
        Job c = new Job("C", "cmdC");
        Job d = new Job("D", "cmdD");

        a.addDep(b);
        a.addDep(c);
        b.addDep(d);

        List<Job> jobs = Arrays.asList(a,b,c,d);

        printJobs(jobs);

        /*
         Normal:
         cmdD
         cmdB
         cmdC
         cmdA
         */

        // Tricky:
        // shared dependency

        // Edge:
        // empty input

        // Stress:
        // deep chain
    }
}