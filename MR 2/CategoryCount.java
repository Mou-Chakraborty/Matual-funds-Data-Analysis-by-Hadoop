import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CategoryCount {

    public static class CategoryMapper
            extends Mapper<LongWritable, Text, Text, IntWritable> {

        private static final IntWritable ONE =
                new IntWritable(1);

        public void map(LongWritable key, Text value,
                Context context)
                throws IOException, InterruptedException {

            String line = value.toString();

            if (line.startsWith("Fund_House"))
                return;

            String[] fields = line.split(",", -1);

            if (fields.length >= 7) {

                String category = fields[2];

                if (!category.isEmpty()) {

                    context.write(
                        new Text(category),
                        ONE
                    );
                }
            }
        }
    }

    public static class CategoryReducer
            extends Reducer<Text, IntWritable, Text, IntWritable> {

        public void reduce(Text key,
                Iterable<IntWritable> values,
                Context context)
                throws IOException, InterruptedException {

            int total = 0;

            for (IntWritable value : values) {
                total += value.get();
            }

            context.write(
                key,
                new IntWritable(total)
            );
        }
    }

    public static void main(String[] args)
            throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(
            conf,
            "Scheme Category Count"
        );

        job.setJarByClass(CategoryCount.class);

        job.setMapperClass(CategoryMapper.class);
        job.setReducerClass(CategoryReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

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