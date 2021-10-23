package com.trian.data.remote

import com.google.android.gms.tasks.Task

/**
 * Manages data access for POJOs that are uniquely identifiable by a key, such as POJOs implementing {@link Identifiable}.
 */
interface BaseRepository<TEntity : Identifiable<TKey>,TKey> {

    /**
     * Checks the repository for a given id and returns a boolean representing its existence.
     * @param id the unique id of an entity.
     * @return A {@link Task} for a boolean which is 'true' if the entity for the given id exists, 'false' otherwise.
     */
    fun exists(id:TKey):Task<Boolean>

    /**
     * Queries the repository for an uniquely identified entity and returns it. If the entity does
     * not exist in the repository, a new instance is returned.
     * @param id the unique id of an entity.
     * @return A [Task] for an entity implementing [Identifiable].
     */
    operator fun get(id: TKey): Task<TEntity>
    /**
     * Stores an entity in the repository so it is accessible via its unique id.
     * @param entity the entity implementing {@link Identifiable} to be stored.
     * @return An {@link Task} to be notified of failures.
     */
     fun create(entity:TEntity):Task<Void>
    /**
     * Updates an entity in the repository
     * @param entity the new entity to be stored.
     * @return A {@link Task} to be notified of failures.
     */
    open fun update(entity: TEntity): Task<Void>

    /**
     * Deletes an entity from the repository.
     * @param id uniquely identifying the entity.
     * @return A [Task] to be notified of failures.
     */
    fun delete(id: TKey): Task<Void>
}