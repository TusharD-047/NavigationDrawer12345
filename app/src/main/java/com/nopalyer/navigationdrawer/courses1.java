package com.nopalyer.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class courses1 extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listItem;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses1);
        expandableListView=findViewById(R.id.expandable_listview);
        listGroup = new ArrayList<>();
        listItem =new HashMap<>();
        adapter= new MainAdapter(this,listGroup,listItem);
        expandableListView.setAdapter(adapter);
        initListData();
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String course = listGroup.get(groupPosition);
                String branch = (String) adapter.getChild(groupPosition,childPosition);
                if (course.equals("B.Tech/Dual Degree(1st year)")){
                    String a = "BtechDualfirst";
                    String b = "Alldep";
                    Intent i = new Intent(courses1.this,CourseView.class);
                    i.putExtra("Course",a);
                    i.putExtra("branch",b);
                    startActivity(i);
                }else if (course.equals("B.Arch(1st year)")){
                    String a = "Barchfirst";
                    String b = "Barchfir";
                    Intent i = new Intent(courses1.this,CourseView.class);
                    i.putExtra("Course",a);
                    i.putExtra("branch",b);
                    startActivity(i);
                }else if (course.equals("B.Tech(2nd year and onwards)")){
                    String a = "Btechsecond";
                    if (branch.equals("Computer Science and Engineering")){
                        String b = "CSE";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Civil Engineering")){
                        String b = "Civil";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Chemical Engineering")){
                        String b = "Chemical";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Electronics and Communication Engineering")){
                        String b = "ECE";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Electrical Engineering")){
                        String b = "Electrical";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Mechanical Engineering")){
                        String b = "Mechanical";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Material Science and Engineering")){
                        String b = "MS";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Mathematics and Scientific Computing")){
                        String b = "Mathematics";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Engineering Physics")){
                        String b = "Physics";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }
                }else if (course.equals("Dual Degree(2nd year and onwards)")){
                    String a = "Dualsecond";
                    if (branch.equals("Computer Science and Engineering(DD)")){
                        String b = "CSEDD";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }else if (branch.equals("Electronics and Communication Engineering(DD)")){
                        String b = "ECEDD";
                        Intent i = new Intent(courses1.this,CourseView.class);
                        i.putExtra("Course",a);
                        i.putExtra("branch",b);
                        startActivity(i);
                    }
                }else if (course.equals("B.Arch(2nd year and onwards)")){
                    String a = "Barchsecond";
                    String b = "Barchsec";
                    Intent i = new Intent(courses1.this,CourseView.class);
                    i.putExtra("Course",a);
                    i.putExtra("branch",b);
                    startActivity(i);
                }

                return false;
            }
        });
    }
    private void initListData(){
        listGroup.add(getString(R.string.group1));
        listGroup.add(getString(R.string.group2));
        listGroup.add(getString(R.string.group3));
        listGroup.add(getString(R.string.group4));
        listGroup.add(getString(R.string.group5));

        String[] array;
        List<String> list1= new ArrayList<>();
        array = getResources().getStringArray(R.array.group1);
        for(String item:array){
            list1.add(item);
        }
        List<String> list2= new ArrayList<>();
        array = getResources().getStringArray(R.array.group2);
        for(String item:array){
            list2.add(item);
        }
        List<String> list3 =new ArrayList<>();
        array = getResources().getStringArray(R.array.group3);
        for(String item:array){
            list3.add(item);
        }
        List<String> list4= new ArrayList<>();
        array = getResources().getStringArray(R.array.group4);
        for(String item:array){
            list4.add(item);
        }
        List<String> list5= new ArrayList<>();
        array = getResources().getStringArray(R.array.group5);
        for(String item:array){
            list5.add(item);
        }
        listItem.put(listGroup.get(0),list1);
        listItem.put(listGroup.get(1),list2);
        listItem.put(listGroup.get(2),list3);
        listItem.put(listGroup.get(3),list4);
        listItem.put(listGroup.get(4),list5);
        adapter.notifyDataSetChanged();
    }
}