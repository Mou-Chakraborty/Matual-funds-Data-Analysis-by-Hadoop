import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxNAV {

    public static class MaxMapper
            extends Mapper<LongWritable, Text, Text, DoubleWritable> {

        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {

            String line = value.toString();

            if (line.startsWith("Fund_House"))
                return;

            String[] fields = line.split(",", -1);

            try {
                double nav = Double.parseDouble(fields[6]);

                context.write(
                    new Text("MAX"),
                    new DoubleWritable(nav)
                );

            } catch (Exception e) {
                // Ignore invalid records
            }
        }
    }

    public static class MaxReducer
            extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        public void reduce(Text key, Iterable<DoubleWritable> values,
                Context context)
                throws IOException, InterruptedException {

            double max = Double.MIN_VALUE;

            for (DoubleWritable value : values) {

                if (value.get() > max) {
                    max = value.get();
                }
            }

            context.write(
                new Text("Maximum NAV"),
                new DoubleWritable(max)
            );
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "Maximum NAV");

        job.setJarByClass(MaxNAV.class);

        job.setMapperClass(MaxMapper.class);
        job.setReducerClass(MaxReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(
            job,
            new Path(args[0])
        );

        FileOutputFormat.setOutputPath(
            job,
            new Path(args[1])
        );

        System.exit(
            job.waitForCompletion(true) ? 0 : 1
        );
    }
}