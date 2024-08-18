package com.gianghv.android.repository.api

import com.gianghv.android.domain.Project
import com.gianghv.android.domain.Researcher
import com.gianghv.android.domain.Supervisor
import com.gianghv.android.repository.FakeData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

object FirebaseListener {
    @OptIn(DelicateCoroutinesApi::class)
    fun listenFirebase() {
        GlobalScope.launch(Dispatchers.IO) {
            listen()
        }
    }

    fun getData() {
        getDataResearcher()
    }

    private fun getDataResearcher() {
        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        val database = firebase.database.reference

        Timber.d("listen")
        database.child("researchers").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val researchers = mutableListOf<Researcher>()
                    snapshot.children.forEach {
                        val researcher = it.getResearcher()

                        if (researcher != null) {
                            Timber.d("researcher $researcher")
                            researchers.add(researcher)
                        }
                    }

                    FakeData.researchers.clear()
                    FakeData.researchers.addAll(researchers)

                    Timber.d("DATA researcher ${FakeData.researchers}")
                    getDataSupervisor()
                } catch (e: Exception) {
                    Timber.e("error $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    private fun getDataSupervisor(){
        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        val database = firebase.database.reference
        database.child("supervisors").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val supervisors = mutableListOf<Supervisor>()
                    snapshot.children.forEach {
                        val supervisor = it.getSupervisor()
                        if (supervisor != null) {
                            Timber.d("supervisor $supervisor")
                            supervisors.add(supervisor)
                        }
                    }

                    FakeData.supervisors.clear()
                    FakeData.supervisors.addAll(supervisors)

                    Timber.d("DATA supervisor ${FakeData.supervisors}")
                    getDataProject()
                } catch (e: Exception) {
                    Timber.e("error $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun getDataProject() {
        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        val database = firebase.database.reference
        database.child("projects").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val projects = mutableListOf<Project>()
                    snapshot.children.forEach {
                        val project = it.getProject()
                        if (project != null) {
                            Timber.d("project $project")

                            projects.add(project)
                        }
                    }

                    FakeData.projects.clear()
                    FakeData.projects.addAll(projects)
                    Timber.d("DATA project ${FakeData.projects}")
                } catch (e: Exception) {
                    Timber.e("error $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun listen() {
        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        val database = firebase.database.reference

        Timber.d("listen")
        database.child("researchers").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val researchers = mutableListOf<Researcher>()
                    snapshot.children.forEach {
                        val researcher = it.getResearcher()

                        if (researcher != null) {
                            Timber.d("researcher $researcher")
                            researchers.add(researcher)
                        }
                    }

                    FakeData.researchers.clear()
                    FakeData.researchers.addAll(researchers)

                    Timber.d("DATA researcher ${FakeData.researchers}")
                } catch (e: Exception) {
                    Timber.e("error $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        database.child("supervisors").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val supervisors = mutableListOf<Supervisor>()
                    snapshot.children.forEach {
                        val supervisor = it.getSupervisor()
                        if (supervisor != null) {
                            Timber.d("supervisor $supervisor")
                            supervisors.add(supervisor)
                        }
                    }

                    FakeData.supervisors.clear()
                    FakeData.supervisors.addAll(supervisors)

                    Timber.d("DATA supervisor ${FakeData.supervisors}")
                } catch (e: Exception) {
                    Timber.e("error $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        database.child("projects").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val projects = mutableListOf<Project>()
                    snapshot.children.forEach {
                        val project = it.getProject()
                        if (project != null) {
                            Timber.d("project $project")

                            projects.add(project)
                        }
                    }

                    FakeData.projects.clear()
                    FakeData.projects.addAll(projects)
                    Timber.d("DATA project ${FakeData.projects}")
                } catch (e: Exception) {
                    Timber.e("error $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
